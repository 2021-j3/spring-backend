package com.ecommerce.j3.domain.mapper;

import com.ecommerce.j3.controller.dto.OrderDto;
import com.ecommerce.j3.domain.entity.*;
import com.ecommerce.j3.domain.entity.embedded.OrderDetails;
import com.ecommerce.j3.domain.entity.embedded.PayInfo2;
import com.ecommerce.j3.repository.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public class CommonMapper {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private TagRepository tagRepository;

    @Mapping(target="addresses", ignore = true)
    Account mapIdToAccount(Long accountId) {
        return accountRepository.findById(accountId).orElse(null);
    }

    Cart mapIdToCart(Long cartId) {
        return cartRepository.findById(cartId).orElse(null);
    }

    List<CartItem> mapIdsToCartItems(List<Long> cartItemIds) {
        if (cartItemIds == null) return null;
        return cartItemRepository.getByCartItemIdIn(cartItemIds);
    }

    Category mapIdToCategory(Long categoryId) {
        return categoryRepository.findById(categoryId).orElse(null);
    }

    Order mapIdToOrder(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    OrderItem mapIdToOrderItem(Long orderItemId) {
        return orderItemRepository.findById(orderItemId).orElse(null);
    }

    List<OrderItem> mapIdsToOrderItems(List<Long> orderItemIds) {
        if (orderItemIds == null) return null;
        return orderItemRepository.getByOrderItemIdIn(orderItemIds);
    }

    Product mapIdToProduct(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }

    Review mapIdToReview(Long reviewId) {
        return reviewRepository.findById(reviewId).orElse(null);
    }

    Set<Category> mapIdsToCategorySet(Set<Long> categoryIds){
        if (categoryIds == null) return null;
        return categoryRepository.getByCategoryIdIn(categoryIds);
    }

    Set<Tag> mapIdsToTagSet(Set<Long> tagIds){
        if (tagIds == null) return null;
        return tagRepository.getByTagIdIn(tagIds);
    }

    Long mapCategoryToCategoryId(Category category){
        if(category == null) return null;
        else return category.getCategoryId();
    }
    Long mapAccountToAccountId(Account account){
        return account.getAccountId();
    }

    PayInfo2 mapPayInfoToPayInfo2(OrderDto.PayInfo payInfo){
        return new PayInfo2(payInfo.getItemPriceTotal(), payInfo.getItemDiscount(), payInfo.getTax(), payInfo.getShipping(), payInfo.getUserDiscount(), payInfo.getGrandTotal());
    }

    OrderDto.PayInfo mapPayInfoToPayInfo2(PayInfo2 payInfo){
        return new OrderDto.PayInfo(payInfo.getItemPriceTotal(), payInfo.getItemDiscount(), payInfo.getTax(), payInfo.getShipping(), payInfo.getUserDiscount(), payInfo.getGrandTotal());
    }

    List<OrderItem> mapOrderITemsToOrderItemList(OrderDetails orderItems){
        if (orderItems == null) return null;
        return orderItems.getItems();
    }

    OrderDetails mapOrderITemListToOrderItems(List<OrderItem> orderItemList){
        if(orderItemList == null) return null;
        return new OrderDetails(orderItemList);
    }

    OrderDetails mapOrderItemIdsToOrderItems(List<Long> orderItemsIds){
        if (orderItemsIds == null) return null;
        return new OrderDetails(mapIdsToOrderItems(orderItemsIds));
    }
}
