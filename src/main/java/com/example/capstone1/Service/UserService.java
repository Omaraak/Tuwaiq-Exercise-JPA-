package com.example.capstone1.Service;

import com.example.capstone1.Model.Merchant;
import com.example.capstone1.Model.MerchantStock;
import com.example.capstone1.Model.Product;
import com.example.capstone1.Model.User;
import com.example.capstone1.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final ProductService productService;
    private final MerchantService merchantService;
    private final MerchantStockService merchantStockService;

    public void addUser(User user) {
        userRepository.save(user);
    }

    public List<User> getUserRepository() {
        return userRepository.findAll();
    }

    public boolean updateUser(User user, int id) {
        User oldUser = userRepository.getById(id);
        if (oldUser != null) {
            oldUser.setUserName(user.getUserName());
            oldUser.setEmail(user.getEmail());
            oldUser.setPassword(user.getPassword());
            oldUser.setRole(user.getRole());
            oldUser.setBalance(user.getBalance());
            userRepository.save(oldUser);
            return true;
        }
        return false;
    }

    public boolean deleteUser(int id) {
        User user = userRepository.getById(id);
        if (user != null) {
            userRepository.delete(user);
            return true;
        }
        return false;
    }

    public  String buy(int userId, int productId, int merchantId) {
        for (User user : userRepository.findAll()) {
            if (user.getId() == userId) {
                for (Merchant merchant : merchantService.getMerchants()) {
                    if (merchant.getId() == merchantId) {
                        for (Product product : productService.getProductRepository()) {
                            if (product.getId() == productId) {
                                for (MerchantStock merchantStock : merchantStockService.getMerchantStockRepository()) {
                                    if (merchantStock.getMerchantID() == merchantId && merchantStock.getProductID() == productId) {
                                        if (user.getBalance() < product.getPrice()) {
                                            return "Balance to low";
                                        }
                                        else if (merchantStock.getStock() <= 0) {
                                            return "Out of stock";
                                        }
                                        merchantStock.setStock(merchantStock.getStock() - 1);
                                        user.setBalance(user.getBalance() - product.getPrice());
                                        product.setBoughtDate(LocalDate.now());
                                        return "Successfully bought product";
                                    }
                                }
                            }
                            return "Product not found";
                        }
                    }
                }
                return "Merchant not found";
            }
        }
        return "User not found";
    }
}

