package pouryapb.addressbook;

import java.io.Serializable;
import java.util.ArrayList;

public class Group implements Serializable{

	/**
	 * not so complicated!
	 * not even finished!!
	 */
	private static final long serialVersionUID = -3981933287694524625L;
	public ArrayList<String> groups = new ArrayList<>();
	
	public void addNewGroup(String name) {
		groups.add(name);
	}
	
	// TODO
	public String[] getGroupMembers(String name, ArrayList<Contact> arg0) {
		String[] list = new String[arg0.size()];
		
		for (int i = 0; i < arg0.size(); i++) {
			if (arg0.get(i).getGroup().equals(name)) {
				list[i] = arg0.get(i).getName();
			}
		}
		
		return list;
	}
	
	public String[] getGroupNames(Group arg0) {
		String[] list = new String[arg0.groups.size()];
		
		for (int i = 0; i < arg0.groups.size(); i++) {
			list[i] = arg0.groups.get(i);
		}
		
		return list;
	}
	
	public String[] getGroups() {
		String[] res = new String[groups.size() + 1];
		res = groups.toArray(res);
		res[groups.size()] = "Other";
		return res;
	}
	
}
