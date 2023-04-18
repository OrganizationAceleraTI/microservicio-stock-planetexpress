package co.acelerati.planetexpress.application.mapper;

import co.acelerati.planetexpress.domain.model.DetailStock;
import co.acelerati.planetexpress.domain.model.product.Brand;
import co.acelerati.planetexpress.domain.model.product.Category;
import co.acelerati.planetexpress.domain.model.product.Product;
import co.acelerati.planetexpress.infraestructure.http.rest.dto.response.BrandResponseDTO;
import co.acelerati.planetexpress.infraestructure.http.rest.dto.response.CategoryResponseDTO;
import co.acelerati.planetexpress.infraestructure.http.rest.dto.response.DetailStockResponseDTO;
import co.acelerati.planetexpress.infraestructure.http.rest.dto.response.ProductResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class StockRequestMapper {

    private StockRequestMapper() {  }
    public static DetailStockResponseDTO toProductDTO(DetailStock detailStock) {
        return new DetailStockResponseDTO(
          detailStock.getId(),
          detailStock.getName(),
          detailStock.getDescription(),
          detailStock.getModel(),
          detailStock.getBrand(),
          detailStock.getCategory(),
          detailStock.getQuantity(),
          detailStock.getCurrentPrice());
    }

    public static List<DetailStockResponseDTO> toProductDTOList(List<DetailStock> detailStocks) {
        return detailStocks.stream()
          .map(InventorySupplyRequestMapper::toProductDTO)
          .collect(Collectors.toList());
    }

    public static List<Brand> toBrandList(List<BrandResponseDTO> brandResponseDTOS){
        return brandResponseDTOS.stream()
          .map(StockRequestMapper::toBrand)
          .collect(Collectors.toList());
    }

    public static List<Category> toCategoryList(List<CategoryResponseDTO> categoryResponseDTOS){
        return categoryResponseDTOS.stream()
          .map(StockRequestMapper::toCategory)
          .collect(Collectors.toList());
    }

    public static List<Product> toProductList(List<ProductResponseDTO> productResponseDTOs) {
        return productResponseDTOs.stream()
          .map(StockRequestMapper::toProduct)
          .collect(Collectors.toList());
    }

    public static Product toProduct(ProductResponseDTO productResponseDTO) {
        return new Product(
          productResponseDTO.getId(),
          productResponseDTO.getName(),
          productResponseDTO.getDescription(),
          productResponseDTO.getModel(),
          productResponseDTO.getIdBrand(),
          productResponseDTO.getIdCategory());
    }

    public static Category toCategory(CategoryResponseDTO categoryResponseDTO) {
        return new Category(categoryResponseDTO.getId(), categoryResponseDTO.getName());
    }

    public static Brand toBrand(BrandResponseDTO brandResponseDTO) {
        return new Brand(brandResponseDTO.getId(), brandResponseDTO.getName());
    }
}
