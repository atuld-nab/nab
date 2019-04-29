package au.com.nab.service;

import au.com.nab.Exception.CurrencyServiceException;
import au.com.nab.LoadDataBase;
import au.com.nab.model.Currency;
import au.com.nab.model.PriceTimeStamp;
import au.com.nab.model.ProfitResponseModel;
import au.com.nab.repository.CurrencyRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;

@Service("currencyService")
public class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    CurrencyRepository repository;

    Logger log = getLogger(CurrencyServiceImpl.class);
    @Override
    public List<ProfitResponseModel> computeDailyProfit() throws CurrencyServiceException {
        log.info("fetching data for all currency codes daily profit");
        List<ProfitResponseModel> responseModelList = null;
        try
        {

            responseModelList = new ArrayList<>();
            String [] codes = {"BTC","ETC","LTC"};
            for(String code :codes)
            { log.info("code"+code);
                responseModelList.add(computeDailyProfitByCode(code));
            }

        }catch(Exception ex)
        {
            log.error("error occured while computing daily profit"+ex.getMessage());
            throw new CurrencyServiceException("error occured while computing daily profit"+ex.getMessage());
        }
        return responseModelList;
    }

    @Override
    public ProfitResponseModel computeDailyProfitByCode(final String currencyCode) throws CurrencyServiceException {
       log.info("fetching profit for currency code"+currencyCode);
        ProfitResponseModel maxDailyProfit = null;

       try
       {
           List <Currency>codeList =repository.findAll().stream().filter(currency->currency.getName().
                   equalsIgnoreCase(currencyCode)).collect(Collectors.toList());

           List<Currency> sortedCodeList = null;
           Double priceDiff =0.0;
           LocalDateTime buyTimeStamp = null;
           LocalDateTime sellTimeStamp = null;
           Double buyingPrice=null;
           Double sellinPrice= null;

           for(Currency oldList : codeList)
           {
              sortedCodeList =
                       codeList.stream().filter(currency ->
                               (currency.getTime().isAfter(oldList.getTime()))).collect(Collectors.toList());
              if(sortedCodeList.size()>0)
               for (Currency sortedCurrency : sortedCodeList)
               {
                   if(sortedCurrency.getPrice()-oldList.getPrice()>priceDiff)
                   {
                       priceDiff=sortedCurrency.getPrice()-oldList.getPrice();
                       buyTimeStamp = oldList.getTime();
                       buyingPrice=oldList.getPrice();
                       sellinPrice=sortedCurrency.getPrice();
                       sellTimeStamp = sortedCurrency.getTime();

                   }
               }
           }

           // we got profit
           if(priceDiff>0.0 && buyTimeStamp != null && sellTimeStamp != null)
           {
               log.info("got profit"+priceDiff);
               maxDailyProfit= populateWebResponse(new PriceTimeStamp(buyTimeStamp.toLocalTime(),buyingPrice),new PriceTimeStamp(sellTimeStamp.toLocalTime(),sellinPrice),currencyCode);


           }
           else

               maxDailyProfit = new ProfitResponseModel();



       } catch(Exception ex)
       {
           log.error("error occured while computing profit for currency code"+currencyCode+"<<<<"+ex.getMessage());
           throw new CurrencyServiceException("error occured while computing profit for currency code"+currencyCode+"<<<"+ex.getMessage());
       }
       return maxDailyProfit;

    }

    private ProfitResponseModel populateWebResponse(final PriceTimeStamp buy,final PriceTimeStamp sell,final String code)
    {
        ProfitResponseModel responseModel = new ProfitResponseModel();
        responseModel.setCurrency(code);
        responseModel.setBuyingPrice(buy);
        responseModel.setProfit(sell.getPrice()-buy.getPrice());
        responseModel.setSellPrice(sell);
        return responseModel;
    }
}
