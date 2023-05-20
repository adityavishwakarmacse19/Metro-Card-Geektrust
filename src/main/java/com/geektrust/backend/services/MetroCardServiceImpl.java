package com.geektrust.backend.services;

import com.geektrust.backend.constants.Common;
import com.geektrust.backend.models.MetroCard;
import com.geektrust.backend.repositories.CardRepository;
import com.geektrust.backend.repositories.CardRepositoryImpl;

public class MetroCardServiceImpl implements CardService{

    private CardRepository cardRepository = new CardRepositoryImpl();

    @Override
    public void balance(String cardNumber, int balance) {
        MetroCard card = new MetroCard(cardNumber, balance);
        cardRepository.save(card);
    }

    @Override
    public int useCard(String cardNumber, int cost) {
        MetroCard card = cardRepository.getByCardNumber(cardNumber);
        int balance = card.getBalance();
        if(cost > card.getBalance()){
            int sufficientAmtToLoad = cost - card.getBalance();
            balance = Common.ZERO;
            card.setBalance(balance);
            cardRepository.save(card);
            return sufficientAmtToLoad;
        } else {
            balance = card.getBalance() - cost;
            card.setBalance(balance);
            cardRepository.save(card);
            return Common.ZERO;
        }
    }

    @Override
    public MetroCard getCardByCardNumber(String cardNumber){
        return cardRepository.getByCardNumber(cardNumber);
    }

}
