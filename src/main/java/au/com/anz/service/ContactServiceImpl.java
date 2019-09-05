package au.com.anz.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import au.com.anz.Exception.ContactServiceException;
import au.com.anz.controller.BasicContactResponse;
import au.com.anz.model.Contact;

@Service("contactService")
public class ContactServiceImpl implements ContactService {

	private static final Logger log = Logger.getLogger("ContactServiceImpl.class");

	@Override
	public BasicContactResponse compareAddressBook(final String addressBook1, final String addressBook2)
			throws ContactServiceException {
		log.info("getting contact unique to each address book");
		BasicContactResponse compare = new BasicContactResponse();
		List<Contact> result = new ArrayList<>();
		BasicContactResponse addressbook1 = findByAddressBook(addressBook1);
		BasicContactResponse addressbook2 = findByAddressBook(addressBook2);
		try
		{
		if (!addressbook2.getResponse().isEmpty() && (!addressbook1.getResponse().isEmpty())) {
			for (Contact iterator : addressbook2.getResponse()) {
				if (addressbook1.getResponse().stream()
						.noneMatch(o -> o.getName().equalsIgnoreCase(iterator.getName())))
					result.add(iterator);
				

			}
			for (Contact iterator : addressbook1.getResponse()) {
				if (addressbook2.getResponse().stream()
						.noneMatch(o -> o.getName().equalsIgnoreCase(iterator.getName())))
					result.add(iterator);
				

			}
			
			if(!result.isEmpty())
			{
				log.info("getting contact unique to each address book with size"+result);
			}
		}
		}
		catch(Exception ex)
		{
			throw new ContactServiceException("contactException in compareAddressBook because" + ex.getMessage());

		}
		compare.setResponse(result);
		return compare;
	}

	@Override
	public BasicContactResponse findByAddressBook(final String addressBook) throws ContactServiceException {
		// TODO Auto-generated method stub
		log.info("Mapping csv file to model");
		BasicContactResponse response = new BasicContactResponse();
		InputStream is = null;
		List<Contact> transList = new ArrayList<>();
		BufferedReader br = null;
		try {
			is = new FileInputStream(new File(addressBook+".csv"));
			br = new BufferedReader(new InputStreamReader(is));
			transList.addAll(br.lines().map(mapToTransaction).collect(Collectors.toList()));
			transList.sort(Comparator.comparing(Contact::getName));
		} catch (NullPointerException | FileNotFoundException ex) {

			throw new ContactServiceException("contactException in findByAddressBook because" + ex.getMessage());

		} finally {
			try {
				if (null != is && null != br) {
					br.close();
					is.close();
				}
			} catch (IOException e) {
				log.log(Level.SEVERE, "ContactServiceException in findByAddressBook class finally block", e);
			}
		}
		response.setResponse(transList);
		return response;
	}

	@Override
	public void appendContact(String addressBook, Contact data) throws ContactServiceException {
		log.info("publishing data to  csv file"+data.toString()+"to file "+addressBook);
		BufferedWriter bw = null;
		FileWriter fw = null;
		boolean oldfile = true;
		
		try
		{
			File file = new File(addressBook+".csv");
			if(!file.exists())
			{
				file.createNewFile();
				oldfile= false;
				bw  = new BufferedWriter (new FileWriter(file.getAbsoluteFile()));
			}
			
			
			if(oldfile && file.length()>0)
			{
				bw  = new BufferedWriter (new FileWriter(file.getAbsoluteFile(),true));
				
			}
			bw.append(data.getName());
			bw.append(",");
			bw.append(data.getPhone());
			bw.append(System.lineSeparator());
			bw.flush();
			
		}catch (IOException | NullPointerException ex)
		{
			throw new ContactServiceException("contactException in appendContact because" + ex.getMessage());

		} 
		finally
		{

			try {
				if (null != bw && null != fw) {
					bw.close();
					fw.close();
				}
			} catch (IOException e) {
				log.log(Level.SEVERE, "ContactServiceException in appendContact method finally block", e);
			}
		
		}

	}

	private static Function<String, Contact> mapToTransaction = (line) -> {
		Contact entry = null;
		String[] p = line.split(",");
		if (p != null && p.length > 0)
			entry = new Contact(p[0], (p[1]));
		return entry;
	};

}
