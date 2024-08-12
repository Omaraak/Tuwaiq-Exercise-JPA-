package com.example.capstone1.Service;

import com.example.capstone1.Model.Merchant;
import com.example.capstone1.Model.MerchantStock;
import com.example.capstone1.Model.Product;
import com.example.capstone1.Repository.MerchantStockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchantStockService {
    private final MerchantStockRepository merchantStockRepository;
    private final ProductService productService;
    private final MerchantService merchantService;

    public String addMerchantStock(MerchantStock merchantStock) {
        for (Product product : productService.getProductRepository()) {
            if (product.getId() == merchantStock.getProductID()) {
                for (Merchant merchant : merchantService.getMerchants()) {
                    if (merchant.getId() == merchantStock.getMerchantID()) {
                        merchantStockRepository.save(merchantStock);
                        return "Merchant stock added successfully";
                    }
                }
                return "Merchant not found";
            }
        }
        return "Product not found";

    }

    public List<MerchantStock> getMerchantStockRepository() {
        return merchantStockRepository.findAll();
    }

    public MerchantStock getMerchantStock(int productId, int merchantId) {
        for (MerchantStock merchantStock : merchantStockRepository.findAll()) {
            if (merchantStock.getProductID() == productId && merchantStock.getMerchantID() == merchantId) {
                return merchantStock;
            }
        }
        return null;
    }

    public boolean updateMerchantStock(MerchantStock merchantStock, int id) {
        MerchantStock old = getMerchantStock(id, merchantStock.getMerchantID());
        if (old != null) {
            old.setProductID(merchantStock.getProductID());
            old.setMerchantID(merchantStock.getMerchantID());
            old.setStock(merchantStock.getStock());
            merchantStockRepository.save(old);
            return true;
        }
        return false;
    }

    public boolean deleteMerchantStock(int id) {
        MerchantStock merchantStock = merchantStockRepository.getById(id);
        if (merchantStock != null) {
            merchantStockRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean restockProduct(int productId, int merchantId, int amount) {
        MerchantStock temp = getMerchantStock(productId, merchantId);
        if (temp == null) {
            return false;
        }
        temp.setStock(temp.getStock() + amount);
        return true;
    }
}
