package pouryapb.addressbook;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class AddressBook implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7123771238913726550L;
	public ArrayList<Contact> contacts = new ArrayList<Contact>();
	public Group group = new Group();
	private int cCount = 0;
	private JList<String> list;
	private JTextField nameField;
	private JTextField numField;
	private JTextField yearField;
	private JTextField monthField;
	private JTextField dayField;
	private JTextField addressField;
	private JTextField groupField;
	private JTextField emailField;
	
	
	
	public AddressBook() {
		group.addNewGroup("Unassigned");
		group.addNewGroup("Familly");
		group.addNewGroup("Friends");
		group.addNewGroup("Favorite");
	}

	// adding new Contact
	public void addContact(Contact contact) {
		contacts.add(contact);
		cCount++;
	}
	
	// count of contacts in the address book
	public int getCCount() {
		return cCount;
	}
	
	// remove Contact by name
	public void removeContact(String name) {
		for (int i = 0; i < contacts.size(); i++) {
			if (contacts.get(i).getName().equals(name)) {
				contacts.remove(i);
				cCount--;
				break;
			}
		}
	}
	
	// remove Contact by phoneNumber
	public void removeContact(long phoneNumber) {
		for (int i = 0; i < contacts.size(); i++) {
			if (contacts.get(i).getPhoneNumber() == phoneNumber) {
				contacts.remove(i);
				cCount--;
				break;
			}
		}
	}
	
	// showing a list of contacts
	// adding contact's names to a list
	// Handling mouse events and filling details panel and Edit Panel [details panel and edit panel are connected to gather so... =( ]
	public void showContactList(AddressBook addressBook, JPanel panel, JPanel detail, JPanel edit) {
		DefaultListModel<String> model = new DefaultListModel<>();
		list = new JList<String>(model);
		list.setFont(new Font("SansSerif", Font.PLAIN, 14));
		list.setBackground(UIManager.getColor("InternalFrame.minimizeIconBackground"));
		list.setBounds(20, 72, 284, 336);
		for (int i = 0; i < cCount; i++) {
			model.addElement(contacts.get(i).getName());
		}
		list.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				detail.removeAll();
				
				// creating Contact Details Panel
				JLabel lblContactDetails = new JLabel("Contact Details");
				lblContactDetails.setForeground(Color.DARK_GRAY);
				lblContactDetails.setFont(new Font("Tahoma", Font.PLAIN, 18));
				lblContactDetails.setBounds(10, 11, 145, 46);
				detail.add(lblContactDetails);
				
				JSeparator separator_3 = new JSeparator();
				separator_3.setBounds(10, 55, 216, 2);
				detail.add(separator_3);
				
				JLabel label_17 = new JLabel("Name");
				label_17.setForeground(Color.DARK_GRAY);
				label_17.setFont(new Font("Tahoma", Font.PLAIN, 14));
				label_17.setBounds(20, 68, 35, 17);
				detail.add(label_17);
				
				JLabel label_18 = new JLabel("Phone Number");
				label_18.setForeground(Color.DARK_GRAY);
				label_18.setFont(new Font("Tahoma", Font.PLAIN, 14));
				label_18.setBounds(20, 96, 92, 17);
				detail.add(label_18);
				
				JLabel label_19 = new JLabel("Birthday");
				label_19.setForeground(Color.DARK_GRAY);
				label_19.setFont(new Font("Tahoma", Font.PLAIN, 14));
				label_19.setBounds(20, 124, 51, 17);
				detail.add(label_19);
				
				JLabel label_20 = new JLabel("Address");
				label_20.setForeground(Color.DARK_GRAY);
				label_20.setFont(new Font("Tahoma", Font.PLAIN, 14));
				label_20.setBounds(20, 152, 48, 17);
				detail.add(label_20);
				
				JLabel label_21 = new JLabel("Gender");
				label_21.setForeground(Color.DARK_GRAY);
				label_21.setFont(new Font("Tahoma", Font.PLAIN, 14));
				label_21.setBounds(20, 180, 51, 17);
				detail.add(label_21);
				
				JLabel label_22 = new JLabel("Group");
				label_22.setForeground(Color.DARK_GRAY);
				label_22.setFont(new Font("Tahoma", Font.PLAIN, 14));
				label_22.setBounds(20, 208, 38, 17);
				detail.add(label_22);
				
				JLabel label_23 = new JLabel("E-Mail");
				label_23.setForeground(Color.DARK_GRAY);
				label_23.setFont(new Font("Tahoma", Font.PLAIN, 14));
				label_23.setBounds(20, 236, 51, 17);
				detail.add(label_23);
				
				JButton button = new JButton("Back");
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						CardLayout layout = (CardLayout)panel.getParent().getLayout();
						layout.show(panel.getParent(), "1");
					}
				});
				button.setBounds(186, 298, 89, 23);
				detail.add(button);
				
				int x = list.getSelectedIndex();
				
				JButton btnEdit = new JButton("Edit");
				btnEdit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						edit.removeAll();
						
						// Creating Edit Contact Panel
						JLabel lblNewLabel_1 = new JLabel("Edit Contact");
						lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
						lblNewLabel_1.setForeground(Color.DARK_GRAY);
						lblNewLabel_1.setBounds(10, 11, 145, 46);
						edit.add(lblNewLabel_1);
						
						JSeparator separator_2 = new JSeparator();
						separator_2.setBounds(10, 55, 216, 2);
						edit.add(separator_2);
						
						JLabel label_3 = new JLabel("Name");
						label_3.setForeground(Color.DARK_GRAY);
						label_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
						label_3.setBounds(20, 68, 35, 17);
						edit.add(label_3);
						
						JLabel label_4 = new JLabel("Phone Number");
						label_4.setForeground(Color.DARK_GRAY);
						label_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
						label_4.setBounds(20, 96, 92, 17);
						edit.add(label_4);
						
						JLabel label_5 = new JLabel("Birthday");
						label_5.setForeground(Color.DARK_GRAY);
						label_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
						label_5.setBounds(20, 139, 51, 17);
						edit.add(label_5);
						
						JLabel label_6 = new JLabel("Address");
						label_6.setForeground(Color.DARK_GRAY);
						label_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
						label_6.setBounds(20, 167, 48, 17);
						edit.add(label_6);
						
						JLabel label_7 = new JLabel("Gender");
						label_7.setForeground(Color.DARK_GRAY);
						label_7.setFont(new Font("Tahoma", Font.PLAIN, 14));
						label_7.setBounds(20, 195, 51, 17);
						edit.add(label_7);
						
						JLabel label_8 = new JLabel("Group");
						label_8.setForeground(Color.DARK_GRAY);
						label_8.setFont(new Font("Tahoma", Font.PLAIN, 14));
						label_8.setBounds(20, 223, 38, 17);
						edit.add(label_8);
						
						JLabel label_9 = new JLabel("E-Mail");
						label_9.setForeground(Color.DARK_GRAY);
						label_9.setFont(new Font("Tahoma", Font.PLAIN, 14));
						label_9.setBounds(20, 251, 51, 17);
						edit.add(label_9);
						
						// each field should be filled with contact details [setText()]
						nameField = new JTextField();
						nameField.setColumns(10);
						nameField.setBounds(132, 68, 154, 20);
						nameField.setText(contacts.get(x).getName());
						edit.add(nameField);
						
						numField = new JTextField();
						numField.setColumns(10);
						numField.setBounds(158, 96, 128, 20);
						numField.setText(String.valueOf(contacts.get(x).getPhoneNumber()));
						numField.addKeyListener(new KeyAdapter() {
							public void keyTyped(KeyEvent e) {
								char c = e.getKeyChar();
								// doesn't let user to input more than 9 characters and JUST numbers
								if (!(Character.isDigit(c)) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (numField.getText().length() >= 9)) {
									e.consume();
								}
							}
						});
						edit.add(numField);
						
						// chi begam akhe? :|
						String[] bd = contacts.get(x).getBirthDate().split("/");
						String bds = new String();
						for (int i = 0; i < bd.length; i++) {
							bds += bd[i];
							if (i != bd.length)
								bds += " ";
						}
						// used scanner for its iterator behaves
						@SuppressWarnings("resource")
						Scanner it = new Scanner(bds);
						yearField = new JTextField();
						yearField.setColumns(10);
						yearField.setBounds(132, 139, 51, 20);
						if (it.hasNext()) {
							yearField.setText(String.valueOf(it.next()));
						}
						yearField.addKeyListener(new KeyAdapter() {
							public void keyTyped(KeyEvent e) {
								char c = e.getKeyChar();
								// numbers at last 4 digits
								if (!(Character.isDigit(c)) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (yearField.getText().length() >= 4)) {
									e.consume();
								}
							}
							
							// we are in 2018 so user shouldn't input number greater than 2018 :/
							public void keyReleased(KeyEvent e) {
								try {
									 if (Integer.parseInt(yearField.getText()) > 2018) {
											yearField.setText(yearField.getText().substring(0, yearField.getText().length() - 1));
										}
								} catch (Exception e2) {
									// TODO: handle exception
								}
							}
						});
						edit.add(yearField);
						
						monthField = new JTextField();
						monthField.setColumns(10);
						monthField.setBounds(208, 139, 26, 20);
						if (it.hasNext()) {
							monthField.setText(String.valueOf(it.next()));
						}
						monthField.addKeyListener(new KeyAdapter() {
							public void keyTyped(KeyEvent e) {
								char c = e.getKeyChar();
								// as years this time for month
								if (!(Character.isDigit(c)) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (monthField.getText().length() >= 2)) {
									e.consume();
								}
							}
							// last comment
							public void keyReleased(KeyEvent e) {
								try {
									 if (Integer.parseInt(monthField.getText()) > 12) {
											monthField.setText(monthField.getText().substring(0, monthField.getText().length() - 1));
										}
								} catch (Exception e2) {
									// TODO: handle exception
								}
							}
						});
						edit.add(monthField);
						
						dayField = new JTextField();
						dayField.setColumns(10);
						dayField.setBounds(260, 139, 26, 20);
						if (it.hasNext()) {
							dayField.setText(String.valueOf(it.next()));
						}
						dayField.addKeyListener(new KeyAdapter() {
							public void keyTyped(KeyEvent e) {
								char c = e.getKeyChar();
								// last comment
								if (!(Character.isDigit(c)) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (dayField.getText().length() >= 2)) {
									e.consume();
								}
							}
							
							// last comment
							public void keyReleased(KeyEvent e) {
								try {
									 if (Integer.parseInt(dayField.getText()) > 31) {
											dayField.setText(dayField.getText().substring(0, dayField.getText().length() - 1));
										}
								} catch (Exception e2) {
									// TODO: handle exception
								}
							}
						});
						edit.add(dayField);
						
						addressField = new JTextField();
						addressField.setColumns(10);
						addressField.setBounds(132, 167, 154, 20);
						addressField.setText(contacts.get(x).getAdress());
						edit.add(addressField);
						
						groupField = new JTextField();
						groupField.setColumns(10);
						groupField.setBounds(132, 223, 154, 20);
						groupField.setText(contacts.get(x).getGroup());
						edit.add(groupField);
						
						emailField = new JTextField();
						emailField.setColumns(10);
						emailField.setBounds(132, 251, 154, 20);
						emailField.setText(contacts.get(x).getEmail());
						edit.add(emailField);
						
						JLabel label_10 = new JLabel("/");
						label_10.setForeground(Color.DARK_GRAY);
						label_10.setFont(new Font("Tahoma", Font.PLAIN, 14));
						label_10.setBounds(193, 139, 5, 17);
						edit.add(label_10);
						
						JLabel label_11 = new JLabel("/");
						label_11.setForeground(Color.DARK_GRAY);
						label_11.setFont(new Font("Tahoma", Font.PLAIN, 14));
						label_11.setBounds(244, 139, 5, 17);
						edit.add(label_11);
						
						// should be fixed in updates TODO
						JComboBox<Object> comboBox = new JComboBox<Object>();
						comboBox.setBounds(132, 195, 89, 20);
						comboBox.setModel(new DefaultComboBoxModel<Object>(new String[] {"Choose", "Male", "Female"}));
						edit.add(comboBox);
						
						JButton btnBack = new JButton("Back");
						btnBack.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								// turns back
								CardLayout layout = (CardLayout)edit.getParent().getLayout();
								layout.show(edit.getParent(), "3");
							}
						});
						btnBack.setBounds(186, 298, 89, 23);
						edit.add(btnBack);
						
						JLabel label_12 = new JLabel("09");
						label_12.setForeground(Color.DARK_GRAY);
						label_12.setFont(new Font("Tahoma", Font.PLAIN, 14));
						label_12.setBounds(132, 98, 16, 17);
						edit.add(label_12);
						
						JLabel label_13 = new JLabel("Year:");
						label_13.setBounds(157, 124, 41, 14);
						edit.add(label_13);
						
						JLabel label_14 = new JLabel("Month:");
						label_14.setBounds(200, 124, 48, 14);
						edit.add(label_14);
						
						JLabel label_15 = new JLabel("Day:");
						label_15.setBounds(263, 124, 36, 14);
						edit.add(label_15);
						
						// Error massage
						JLabel lblNameAndPhone = new JLabel("Name and Phone Number are requierd fields!");
						lblNameAndPhone.setFont(new Font("Tahoma", Font.PLAIN, 13));
						lblNameAndPhone.setForeground(Color.RED);
						lblNameAndPhone.setBounds(27, 345, 259, 16);
						
						// Edits contact details
						JButton btnEdit = new JButton("Edit");
						btnEdit.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								if (nameField.getText().isEmpty() || numField.getText().isEmpty()) {
									edit.add(lblNameAndPhone);
									edit.revalidate();
									edit.repaint();
								}
								else if (!nameField.getText().isEmpty() && !numField.getText().isEmpty()) {
									String name = nameField.getText();
									long phoneNumber = Long.valueOf(numField.getText());
									String birthDate = yearField.getText() + "/" + monthField.getText() + "/" + dayField.getText();
									if (birthDate.equals("//")) {
										birthDate = "";
									}
									String address = addressField.getText();
									String gender = "";
									if (!comboBox.getSelectedItem().toString().equals("Choose")) {
										gender = comboBox.getSelectedItem().toString();
									}
									String group = groupField.getText();
									String email = emailField.getText();
									
									contacts.get(x).setName(name);
									contacts.get(x).setPhoneNumber(phoneNumber);
									contacts.get(x).setBirthDate(birthDate);
									contacts.get(x).setAdress(address);
									contacts.get(x).setGroup(group);
									contacts.get(x).setGender(gender);
									contacts.get(x).setEmail(email);
									
									addressBook.saveObject(addressBook);
									
									CardLayout layout = (CardLayout)edit.getParent().getLayout();
									layout.show(edit.getParent(), "1");
									
									nameField.setText("");
									addressField.setText("");
									groupField.setText("");
									comboBox.setSelectedIndex(0);
									emailField.setText("");
									numField.setText("");
									yearField.setText("");
									monthField.setText("");
									dayField.setText("");
									
									edit.remove(lblNameAndPhone);
								}
							}
						});
						btnEdit.setBounds(33, 298, 89, 23);
						edit.add(btnEdit);
						
						CardLayout layout = (CardLayout)panel.getParent().getLayout();
						layout.show(panel.getParent(), "4");
					}
				});
				btnEdit.setBounds(36, 298, 89, 23);
				detail.add(btnEdit);
				
				JLabel nameValue = new JLabel();
				nameValue.setBounds(135, 68, 140, 17);
				nameValue.setText(contacts.get(x).getName());
				nameValue.setToolTipText(contacts.get(x).getName());
				detail.remove(nameValue);
				detail.add(nameValue);
				
				JLabel numberValue = new JLabel();
				numberValue.setToolTipText("");
				numberValue.setBounds(135, 96, 140, 17);
				numberValue.setText(String.valueOf(contacts.get(x).getPhoneNumber()));
				numberValue.setToolTipText(String.valueOf(contacts.get(x).getPhoneNumber()));
				detail.add(numberValue);
				
				JLabel birthValue = new JLabel();
				birthValue.setText(contacts.get(x).getBirthDate());
				birthValue.setToolTipText(contacts.get(x).getBirthDate());
				birthValue.setBounds(135, 124, 140, 17);
				detail.add(birthValue);
				
				JLabel addressValue = new JLabel();
				addressValue.setText(contacts.get(x).getAdress());
				addressValue.setToolTipText(contacts.get(x).getAdress());
				addressValue.setBounds(135, 152, 140, 17);
				detail.add(addressValue);
				
				JLabel genderValue = new JLabel();
				genderValue.setText(contacts.get(x).getGender());
				genderValue.setToolTipText(contacts.get(x).getGender());
				genderValue.setBounds(135, 180, 140, 17);
				detail.add(genderValue);
				
				JLabel groupValue = new JLabel();
				groupValue.setText(contacts.get(x).getGroup());
				groupValue.setToolTipText(contacts.get(x).getGroup());
				groupValue.setBounds(135, 208, 140, 17);
				detail.add(groupValue);
				
				JLabel emailValue = new JLabel();
				emailValue.setText(contacts.get(x).getEmail());
				emailValue.setToolTipText(contacts.get(x).getEmail());
				emailValue.setBounds(135, 236, 140, 17);
				detail.add(emailValue);
				CardLayout layout = (CardLayout)detail.getParent().getLayout();
				layout.show(detail.getParent(), "3");
			}
		});
		JScrollPane sPane = new JScrollPane(list);
		sPane.setBounds(20, 72, 284, 336);
		sPane.setBorder(null);
		sPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		sPane.setBackground(UIManager.getColor("InternalFrame.minimizeIconBackground"));
		panel.add(sPane);
	}
	
	// saving object method
	public void saveObject(AddressBook obj) {
		try {
			// storing //
			FileOutputStream myFileOutputStream = new FileOutputStream("data.ser");
			ObjectOutputStream myObjectOutputStream = new ObjectOutputStream(myFileOutputStream);
			myObjectOutputStream.writeObject(obj);
			myObjectOutputStream.close();
			// storing //
		}
		catch (Exception d) {
			d.printStackTrace();
		}
	}
}
