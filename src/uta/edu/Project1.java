package uta.edu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import com.aonaware.services.webservices.DictServiceSoapProxy;
import com.aonaware.services.webservices.Dictionary;
import com.aonaware.services.webservices.WordDefinition;
import com.aonaware.services.webservices.Definition;

public class Project1 {
	private static DictServiceSoapProxy dc = new DictServiceSoapProxy();
	private static Dictionary[] dl = null;
	private static WordDefinition wd = null;
	private static Definition[] d = null;
	private static int error = 0;

	public static void main(String[] args) throws IOException {
		String word1, dict1;
		BufferedReader input = new BufferedReader(new InputStreamReader(
				System.in));

		dl = dc.dictionaryList();
		for (int i = 0; i < dl.length; i++) {
			System.out.println("Dictionary Name: " + dl[i].getName()
					+ "\n Dictionary Id: " + dl[i].getId() + "\n");
		}
		System.out.println("Enter the Dictionary Id:\n");
		dict1 = input.readLine();
		System.out.println("Enter the Word:");
		word1 = input.readLine();
		meaning(dict1, word1);
		if (error == 1)
			System.out.println("Error");
	}

	private static void meaning(String id, String word) throws RemoteException {

		for (int i = 0; i < dl.length; i++) {
			wd = dc.defineInDict(dl[i].getId(), word);
			d = wd.getDefinitions();
			if (id.length() == 0) {
				for (int j = 0; j < d.length; j++) {
					System.out.println("Dictionary Id: " + dl[i].getId()
							+ "\n Word: " + d[j].getWordDefinition());
				}
				continue;
			} else if (dl[i].getId().equals(id)) {

				for (int j = 0; j < d.length; j++) {
					System.out.println("Dictionary Id: " + dl[i].getId()
							+ "\n Word: " + d[j].getWordDefinition());
				}
				error = 0;
				break;
			} else if (!dl[i].getId().equals(id))
				error = 1;
			// System.out.println("Word: "+dc.dictionaryInfo(dl[i].getId()));
		}
	}
}