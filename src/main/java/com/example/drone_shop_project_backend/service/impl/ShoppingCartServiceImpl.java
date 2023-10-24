package com.example.drone_shop_project_backend.service.impl;

import com.example.drone_shop_project_backend.model.BaseEntity;
import com.example.drone_shop_project_backend.model.Product;
import com.example.drone_shop_project_backend.model.ShoppingCart;
import com.example.drone_shop_project_backend.model.ShoppingCartItem;
import com.example.drone_shop_project_backend.model.dto.shoppingcart.PostShoppingCartItemDto;
import com.example.drone_shop_project_backend.model.dto.shoppingcart.PutShoppingCartDto;
import com.example.drone_shop_project_backend.model.dto.shoppingcart.ShoppingCartDto;
import com.example.drone_shop_project_backend.model.dto.shoppingcart.ShoppingCartItemDto;
import com.example.drone_shop_project_backend.model.mapper.ShoppingCartItemMapper;
import com.example.drone_shop_project_backend.model.mapper.ShoppingCartMapper;
import com.example.drone_shop_project_backend.repository.helper.ProductRepositoryHelper;
import com.example.drone_shop_project_backend.repository.helper.ShoppingCartItemRepositoryHelper;
import com.example.drone_shop_project_backend.repository.helper.ShoppingCartRepositoryHelper;
import com.example.drone_shop_project_backend.service.ShoppingCartService;
import jakarta.transaction.Transactional;
import java.util.Iterator;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {

  private static final ShoppingCartMapper shoppingCartMapper = ShoppingCartMapper.INSTANCE;
  private static final ShoppingCartItemMapper shoppingCartItemMapper = ShoppingCartItemMapper.INSTANCE;

  private final ShoppingCartRepositoryHelper shoppingCartRepositoryHelper;
  private final ShoppingCartItemRepositoryHelper shoppingCartItemRepositoryHelper;
  private final ProductRepositoryHelper productRepositoryHelper;

  @Override
  public ShoppingCartDto getShoppingCart(Long shoppingCartId) {
    ShoppingCart shoppingCart = shoppingCartRepositoryHelper.findById(shoppingCartId);
    ShoppingCartDto shoppingCartDto = shoppingCartMapper.toDto(shoppingCart);
    processNameAndPrice(shoppingCartDto);
    return shoppingCartDto;
  }

  @Transactional
  @Override
  public ShoppingCartDto putShoppingCart(PutShoppingCartDto putShoppingCartDto) {

    //Get shopping cart from database
    ShoppingCart shoppingCart = shoppingCartRepositoryHelper.findById(
        putShoppingCartDto.getShoppingCartId());

    // remove all items from shopping cart
    clear(shoppingCart);

    // add new items to the shopping cart from data transfer object
    List<ShoppingCartItem> newItems = mapToShoppingCartItems(putShoppingCartDto.getItems());
    for (ShoppingCartItem item : newItems) {
      shoppingCart.addItem(item);
    }

    // save to the database
    ShoppingCart savedShoppingCart = shoppingCartRepositoryHelper.save(shoppingCart);

    // mapping shopping cart entity to the data transfer object
    ShoppingCartDto shoppingCartDto = shoppingCartMapper.toDto(savedShoppingCart);
    // filling with name and price from the product
    processNameAndPrice(shoppingCartDto);
    return shoppingCartDto;
  }

  @Override
  public void clear(Long shoppingCartId) {
    ShoppingCart shoppingCart = shoppingCartRepositoryHelper.findById(shoppingCartId);
    clear(shoppingCart);
  }

  @Override
  public void clear(ShoppingCart shoppingCart) {
    // keep ids of items that we have to delete from the database after shopping cart's clearing
    List<Long> oldItemIdList = shoppingCart.getItems()
        .stream()
        .map(BaseEntity::getId)
        .toList();

    // breaking relations between shopping cart and items (just removing items from the shopping cart)
    if (!oldItemIdList.isEmpty()) {
      Iterator<ShoppingCartItem> iterator = shoppingCart.getItems().iterator();
      while (iterator.hasNext()) {
        var item = iterator.next();
        item.setShoppingCart(null);
        iterator.remove();
      }
    }

    // delete all unused items. We need this step to keep db clean
    shoppingCartItemRepositoryHelper.deleteAllByIds(oldItemIdList);
  }

  private void processNameAndPrice(ShoppingCartDto shoppingCartDto) {
    for (ShoppingCartItemDto dto : shoppingCartDto.getItems()) {
      Long productId = dto.getProductId();
      Product product = productRepositoryHelper.findById(productId);
      dto.setName(product.getName());
      dto.setPrice(product.getPrice());
    }
  }

  private List<ShoppingCartItem> mapToShoppingCartItems(List<PostShoppingCartItemDto> items) {
    return items
        .stream()
        .map(shoppingCartItemMapper::toShoppingCartItem)
        .toList();
  }
}
