package au.com.anz.service;

import static org.slf4j.LoggerFactory.getLogger;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import au.com.anz.Exception.TransactionServiceException;
import au.com.anz.controller.BasicAccountResponse;
import au.com.anz.model.BasicAccountEnquiry;
import au.com.anz.repository.BasicAccountEnquiryRepository;

@Service("transactionService")
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	BasicAccountEnquiryRepository repository;

	Logger log = getLogger(TransactionServiceImpl.class);

	@Override
	public BasicAccountResponse findAll() throws TransactionServiceException {
		List<BasicAccountEnquiry> response = null;
		BasicAccountResponse accountResponse = new BasicAccountResponse();
		try {

			response = repository.findAll();
			
			if (null != response && response.size() > 0)
				for(BasicAccountEnquiry iterator:response)
				{
					accountResponse.getResponse().add(extractParentEntity(iterator));
				}
			
				log.info("fetching data for all basic Account Attributes with list size" + response.size());

		} catch (Exception ex) {
			log.error("error while fetching data for all basic Account Attributes" + ex.getMessage());
			throw new TransactionServiceException("error while fetching data for all basic Account Attributes" + ex.getMessage());
		}
		return accountResponse;
	}

	@Override
	public BasicAccountResponse findByAccountNumber(String accountNumber) throws TransactionServiceException {
		// TODO Auto-generated method stub
		BasicAccountEnquiry responseData = null;
		BasicAccountResponse resposnse = new BasicAccountResponse();
		try {
			responseData = repository.findByAccountNumber(accountNumber);
			if (null != responseData)
			{
				log.info("fetching data for all accounts with number:-" + accountNumber);
			List<BasicAccountEnquiry>listAccountDetail = new ArrayList<>();
			listAccountDetail.add(responseData);
			resposnse.setResponse(listAccountDetail);
			}

		} catch (Exception ex) {
			log.error("fetching data for all accounts with number" + ex.getMessage());
			throw new TransactionServiceException("fetching data for all accounts with number" + ex.getMessage());
		}
		return resposnse;

	}
	
	
	private BasicAccountEnquiry extractParentEntity(BasicAccountEnquiry response)
	{
		BasicAccountEnquiry responseData = new BasicAccountEnquiry();
		responseData.setAccountName(response.getAccountName());
		responseData.setAccountNumber(response.getAccountNumber());
		responseData.setBalanceDate(response.getBalanceDate());
		responseData.setCurrency(response.getCurrency());
		responseData.setOpeningBalance(response.getOpeningBalance());
		responseData.setType(response.getType());
		return responseData;
	}

}
