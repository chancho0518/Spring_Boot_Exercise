package com.crud.miniproject.repository.storeSales;

public interface StoreSalesRepository {

    StoreSales findStoreSalesById(Integer storeId);
    void updateSalesAmount(Integer storeId, Integer finalAmount);
}
