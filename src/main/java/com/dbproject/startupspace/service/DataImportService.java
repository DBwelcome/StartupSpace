package com.dbproject.startupspace.service;

import com.dbproject.startupspace.domain.dto.CenterDTO;
import com.dbproject.startupspace.domain.dto.CenterListDTO;
import com.dbproject.startupspace.domain.dto.SpaceDTO;
import com.dbproject.startupspace.domain.dto.SpaceListDTO;
import com.dbproject.startupspace.domain.entity.MetropolitanCenterEntity;
import com.dbproject.startupspace.domain.entity.MetrospaceEntity;
import com.dbproject.startupspace.domain.entity.ProvincialCenterEntity;
import com.dbproject.startupspace.domain.entity.ProvspaceEntity;
import com.dbproject.startupspace.repository.MetropolitanCenterEntityRepository;
import com.dbproject.startupspace.repository.MetrospaceEntityRepository;
import com.dbproject.startupspace.repository.ProvincialCenterEntityRepository;
import com.dbproject.startupspace.repository.ProvspaceEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.UnknownContentTypeException;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Service
public class DataImportService {

    @Autowired
    private MetropolitanCenterEntityRepository metropolitanCenterEntityRepository;
    @Autowired
    private ProvincialCenterEntityRepository provincialCenterEntityRepository;
    @Autowired
    private MetrospaceEntityRepository metrospaceEntityRepository;
    @Autowired
    private ProvspaceEntityRepository provspaceEntityRepository;

    @Scheduled(cron = "0 0 9 * * *")
    public void getRefreshData() throws UnsupportedEncodingException {

        RestTemplate restTemplate = new RestTemplate();

        List<MetropolitanCenterEntity> metropolitanCenterEntityList = metropolitanCenterEntityRepository.findAll();

        for(MetropolitanCenterEntity metropolitanCenterEntity : metropolitanCenterEntityList) {
            URI uri = getCenterSpaceListUri(metropolitanCenterEntity.getCenterId());
            SpaceListDTO spaceListDTO ;
            try {
                spaceListDTO = restTemplate.getForObject(uri, SpaceListDTO.class);
            } catch (UnknownContentTypeException exception) {
                continue;
            }
            System.out.println(spaceListDTO);
            List<MetrospaceEntity> metrospaceEntityList = new ArrayList<>();

            for(SpaceDTO spaceDTO : spaceListDTO.getAreaCenterList()) {

                MetrospaceEntity metrospaceEntity = new MetrospaceEntity(spaceDTO.getSpceId(), new MetropolitanCenterEntity(spaceDTO.getCnterId()), spaceDTO.getSpceNm(),spaceDTO.getSpceTyNm(), spaceDTO.getSeatTyNm(),spaceDTO.getSeatSeNm(),spaceDTO.getDvrAr(),spaceDTO.getCmnuseAr(), spaceDTO.getRentAmt(),spaceDTO.getGrntyAmt(),spaceDTO.getSeatCompany(), 0);
                metrospaceEntityList.add(metrospaceEntity);
            }

            metrospaceEntityRepository.saveAll(metrospaceEntityList);
        }

        List<ProvincialCenterEntity> provincialCenterEntityList = provincialCenterEntityRepository.findAll();

        for (ProvincialCenterEntity provincialCenterEntity : provincialCenterEntityList) {
            URI uri = getCenterSpaceListUri(provincialCenterEntity.getCenterId());
            SpaceListDTO spaceListDTO;
            try {
                spaceListDTO = restTemplate.getForObject(uri, SpaceListDTO.class);
            } catch (UnknownContentTypeException exception) {
                continue;
            }
            System.out.println(spaceListDTO);
            List<ProvspaceEntity> provspaceEntityList = new ArrayList<>();

            for (SpaceDTO spaceDTO : spaceListDTO.getAreaCenterList()) {

                ProvspaceEntity provspaceEntity = new ProvspaceEntity(spaceDTO.getSpceId(), new ProvincialCenterEntity(spaceDTO.getCnterId()), spaceDTO.getSpceNm(), spaceDTO.getSpceTyNm(), spaceDTO.getSeatTyNm(), spaceDTO.getSeatSeNm(), spaceDTO.getDvrAr(), spaceDTO.getCmnuseAr(), spaceDTO.getRentAmt(), spaceDTO.getGrntyAmt(), spaceDTO.getSeatCompany(), 0);
                provspaceEntityList.add(provspaceEntity);
            }

            provspaceEntityRepository.saveAll(provspaceEntityList);
        }
    }


    public void getInitialData() throws UnsupportedEncodingException {

        List<String> metropolitanAreas = Arrays.asList(
                "서울", "인천", "경기"
        );

        List<String> provincialAreas = Arrays.asList(
                "부산", "울산", "대구",
                "경상북도", "광주", "전라남도", "제주",
                "대전", "충청남도", "세종",
                "강원", "충청북도", "전라북도", "경상남도"
        );

        RestTemplate restTemplate = new RestTemplate();

        for(String metropolitanArea : metropolitanAreas) {
            URI uri = getAreaCenterListUri(metropolitanArea);
            CenterListDTO centerListDTO = restTemplate.getForObject(uri, CenterListDTO.class);
            System.out.println(centerListDTO);
            List<MetropolitanCenterEntity> metropolitanCenterEntityList = new ArrayList<>();

            for(CenterDTO centerDTO : centerListDTO.getAreaCenterList()) {
                Integer postNum = null;
                if(centerDTO.getZip()!=null)
                    postNum = Integer.parseInt(centerDTO.getZip());

                MetropolitanCenterEntity metropolitanCenterEntity = new MetropolitanCenterEntity(centerDTO.getCnterId(),centerDTO.getCnterNm(),centerDTO.getCnterTyNm(),centerDTO.getBuldNm(),postNum,centerDTO.getAdr(),centerDTO.getTelnm(),centerDTO.getFxnum(),centerDTO.getEmail(),centerDTO.getHomepage(),centerDTO.getCnterIntrcn(), centerDTO.getSpceCnt());
                metropolitanCenterEntity.setArea(metropolitanArea);
                if(centerDTO.getCnterTyNm().contains("중장년"))
                    metropolitanCenterEntity.setTarget(1);
                else
                    metropolitanCenterEntity.setTarget(0);

                metropolitanCenterEntityList.add(metropolitanCenterEntity);
            }

            metropolitanCenterEntityRepository.saveAll(metropolitanCenterEntityList);
        }

        for(String provincialArea : provincialAreas) {
            URI uri = getAreaCenterListUri(provincialArea);
            CenterListDTO centerListDTO = restTemplate.getForObject(uri, CenterListDTO.class);
            System.out.println(centerListDTO);
            List<ProvincialCenterEntity> provincialCenterEntityList = new ArrayList<>();

            for(CenterDTO centerDTO : centerListDTO.getAreaCenterList()) {
                Integer postNum = null;
                if(centerDTO.getZip()!=null)
                    postNum = Integer.parseInt(centerDTO.getZip());

                ProvincialCenterEntity provincialCenterEntity = new ProvincialCenterEntity(centerDTO.getCnterId(),centerDTO.getCnterNm(),centerDTO.getCnterTyNm(),centerDTO.getBuldNm(),postNum ,centerDTO.getAdr(),centerDTO.getTelnm(),centerDTO.getFxnum(),centerDTO.getEmail(),centerDTO.getHomepage(),centerDTO.getCnterIntrcn(), centerDTO.getSpceCnt());
                provincialCenterEntity.setArea(provincialArea);
                if(centerDTO.getCnterTyNm().contains("중장년"))
                    provincialCenterEntity.setTarget(1);
                else
                    provincialCenterEntity.setTarget(0);

                provincialCenterEntityList.add(provincialCenterEntity);
            }

            provincialCenterEntityRepository.saveAll(provincialCenterEntityList);
        }

        List<MetropolitanCenterEntity> metropolitanCenterEntityList = metropolitanCenterEntityRepository.findAll();

        for(MetropolitanCenterEntity metropolitanCenterEntity : metropolitanCenterEntityList) {
            URI uri = getCenterSpaceListUri(metropolitanCenterEntity.getCenterId());
            SpaceListDTO spaceListDTO ;
            try {
                spaceListDTO = restTemplate.getForObject(uri, SpaceListDTO.class);
            } catch (UnknownContentTypeException exception) {
                continue;
            }
            System.out.println(spaceListDTO);
            List<MetrospaceEntity> metrospaceEntityList = new ArrayList<>();

            for(SpaceDTO spaceDTO : spaceListDTO.getAreaCenterList()) {

                MetrospaceEntity metrospaceEntity = new MetrospaceEntity(spaceDTO.getSpceId(), new MetropolitanCenterEntity(spaceDTO.getCnterId()), spaceDTO.getSpceNm(),spaceDTO.getSpceTyNm(), spaceDTO.getSeatTyNm(),spaceDTO.getSeatSeNm(),spaceDTO.getDvrAr(),spaceDTO.getCmnuseAr(), spaceDTO.getRentAmt(),spaceDTO.getGrntyAmt(),spaceDTO.getSeatCompany(), 0);
                metrospaceEntityList.add(metrospaceEntity);
            }

            metrospaceEntityRepository.saveAll(metrospaceEntityList);
        }

        List<ProvincialCenterEntity> provincialCenterEntityList = provincialCenterEntityRepository.findAll();

        for (ProvincialCenterEntity provincialCenterEntity : provincialCenterEntityList) {
            URI uri = getCenterSpaceListUri(provincialCenterEntity.getCenterId());
            SpaceListDTO spaceListDTO;
            try {
                spaceListDTO = restTemplate.getForObject(uri, SpaceListDTO.class);
            } catch (UnknownContentTypeException exception) {
                continue;
            }
            System.out.println(spaceListDTO);
            List<ProvspaceEntity> provspaceEntityList = new ArrayList<>();

            for (SpaceDTO spaceDTO : spaceListDTO.getAreaCenterList()) {

                ProvspaceEntity provspaceEntity = new ProvspaceEntity(spaceDTO.getSpceId(), new ProvincialCenterEntity(spaceDTO.getCnterId()), spaceDTO.getSpceNm(), spaceDTO.getSpceTyNm(), spaceDTO.getSeatTyNm(), spaceDTO.getSeatSeNm(), spaceDTO.getDvrAr(), spaceDTO.getCmnuseAr(), spaceDTO.getRentAmt(), spaceDTO.getGrntyAmt(), spaceDTO.getSeatCompany(), 0);
                provspaceEntityList.add(provspaceEntity);
            }

            provspaceEntityRepository.saveAll(provspaceEntityList);
        }


    }

    public URI getAreaCenterListUri(String area) throws UnsupportedEncodingException {
        String baseUrl = "http://apis.data.go.kr";
        String serviceKey = "4diGvpxxl3AFrDekiPSCCXAlBkwmp2kQiJLE7Q0SLq5iIHb1fm0c5bbHhdkYP0hhM%2FQ9je0OAKRGvACpczieJQ%3D%3D";

        URI uri = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .path("/B552735/workspaceErumService/getAreaCenterList")
                .queryParam("serviceKey", serviceKey)
                .queryParam("area", URLEncoder.encode(area, "UTF-8"))
                .build(true)
                .toUri();
        return uri;
    }

    public URI getCenterSpaceListUri(int centerId) throws UnsupportedEncodingException {
        String baseUrl = "http://apis.data.go.kr";
        String serviceKey = "4diGvpxxl3AFrDekiPSCCXAlBkwmp2kQiJLE7Q0SLq5iIHb1fm0c5bbHhdkYP0hhM%2FQ9je0OAKRGvACpczieJQ%3D%3D";

        URI uri = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .path("/B552735/workspaceErumService/getCenterSpaceList")
                .queryParam("serviceKey", serviceKey)
                .queryParam("centerId", centerId)
                .build(true)
                .toUri();
        return uri;
    }

}
