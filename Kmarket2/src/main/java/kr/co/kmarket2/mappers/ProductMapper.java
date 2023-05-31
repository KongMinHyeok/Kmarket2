package kr.co.kmarket2.mappers;

/*
 * 날짜: 2023/02/15
 * 이름: 김지홍
 * 내용: mapstruct를 이용하여 Entity-dto 변환하기 
 */
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.web.multipart.MultipartFile;

import kr.co.kmarket2.dto.ProductDTO;
import kr.co.kmarket2.entity.ProductEntity;

/*
 * 날짜 : 2023/02/16
 * 이름 : 김지홍
 * 내용 : Product 의 객체간 맵핑
 */

@Mapper
public interface ProductMapper {
	
	ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
	
    @Mapping(source = "thumb1", target = "thumb1", qualifiedByName = "title")
    @Mapping(source = "thumb2", target = "thumb2", qualifiedByName = "title")
    @Mapping(source = "thumb3", target = "thumb3", qualifiedByName = "title")
    @Mapping(source = "detail", target = "detail", qualifiedByName = "title")
    ProductEntity toEntity(ProductDTO dto);
	
    @Named("title")
    static String fileToTitle(MultipartFile file) {
		return file.getOriginalFilename();
    	
    }
}
