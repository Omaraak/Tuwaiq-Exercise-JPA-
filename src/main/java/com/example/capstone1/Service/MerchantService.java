package com.example.capstone1.Service;

import com.example.capstone1.Model.Merchant;
import com.example.capstone1.Repository.MerchantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchantService {
    private final MerchantRepository merchantRepository;


    public void addMerchant(Merchant merchant) {
        merchantRepository.save(merchant);
    }

    public List<Merchant> getMerchants() {
        return merchantRepository.findAll();
    }

    public Merchant getMerchant(int id) {
        return merchantRepository.findById(id).orElse(null);
    }

    public boolean updateMerchant(Merchant merchant, int id) {
        Merchant oldMerchant = merchantRepository.findById(id).orElse(null);
        if (oldMerchant != null) {
            oldMerchant.setName(merchant.getName());
            return true;
        }
        return false;
    }

    public boolean deleteMerchant(int id) {
        Merchant merchant = merchantRepository.findById(id).orElse(null);
        if (merchant != null) {
            merchantRepository.delete(merchant);
        }
        return false;
    }

}
