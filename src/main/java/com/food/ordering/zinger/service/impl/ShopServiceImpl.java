package com.food.ordering.zinger.service.impl;

import com.food.ordering.zinger.dao.interfaces.AuditLogDao;
import com.food.ordering.zinger.dao.interfaces.ShopDao;
import com.food.ordering.zinger.model.ConfigurationModel;
import com.food.ordering.zinger.model.Response;
import com.food.ordering.zinger.model.ShopConfigurationModel;
import com.food.ordering.zinger.model.logger.ShopLogModel;
import com.food.ordering.zinger.service.interfaces.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    ShopDao shopDao;

    @Autowired
    AuditLogDao auditLogDaoImpl;

    @Override
    public Response<String> insertShop(ConfigurationModel configurationModel) {
        Response<String> response = shopDao.insertShop(configurationModel);
        auditLogDaoImpl.insertShopLog(new ShopLogModel(response, null, configurationModel.toString(), response.priorityGet()));
        return response;
    }

    @Override
    public Response<ShopConfigurationModel> getShopById(Integer shopId) {
        Response<ShopConfigurationModel> response = shopDao.getShopConfigurationById(shopId);
        auditLogDaoImpl.insertShopLog(new ShopLogModel(response, null, shopId.toString(),response.priorityGet()));
        return response;
    }

    @Override
    public Response<List<ShopConfigurationModel>> getShopByPlaceId(Integer placeId) {
        Response<List<ShopConfigurationModel>> response = shopDao.getShopsByPlaceId(placeId);
        auditLogDaoImpl.insertShopLog(new ShopLogModel(response, null, placeId.toString(),response.priorityGet()));
        return response;
    }

    @Override
    public Response<String> updateShopConfiguration(ConfigurationModel configurationModel) {
        Response<String> response = shopDao.updateShopConfigurationModel(configurationModel);
        auditLogDaoImpl.insertShopLog(new ShopLogModel(response, configurationModel.getShopModel().getId(), configurationModel.toString(), response.priorityGet()));
        return response;
    }
}
