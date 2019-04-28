package au.com.nab.service;

import au.com.nab.Exception.CurrencyServiceException;
import au.com.nab.model.ProfitResponseModel;

import java.util.List;

public interface CurrencyService {
    public List<ProfitResponseModel> computeDailyProfit() throws CurrencyServiceException;
    public ProfitResponseModel computeDailyProfitByCode(String currencyCode) throws CurrencyServiceException;
}
