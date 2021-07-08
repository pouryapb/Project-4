package pouryapb.addressbook;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.UIManager;
import javax.swing.AbstractListModel;

public class Gui {

	private JFrame frmContactsV;
	private AddressBook addressBook;
	private JTextField nameField;
	private JTextField addressField;
	private JTextField groupField;
	private JTextField emailField;
	private JTextField phoneNumberField;
	private JTextField yearField;
	private JTextField monthField;
	private JTextField dayField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui window = new Gui();
					window.frmContactsV.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 */
	public Gui() {
		// trying to load object
		// if doesn't exist, storing it to "data.ser".
		try {
			// loading //
			FileInputStream myFileInputStream = new FileInputStream("data.ser");
			ObjectInputStream myObjectInputStream = new ObjectInputStream(myFileInputStream);
			addressBook = (AddressBook) myObjectInputStream.readObject(); 
			myObjectInputStream.close();
			// loading //
		}
		catch (ClassNotFoundException | IOException e) {
			try {
				// saving
				addressBook = new AddressBook();
				addressBook.saveObject(addressBook);
				
			}
			catch (Exception d) {
				d.printStackTrace();
			}
		}
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmContactsV = new JFrame();
		frmContactsV.setTitle("Contacts - V0.01.15 | NOT FINAL");
		frmContactsV.setResizable(false);
		frmContactsV.getContentPane().setFont(new Font("SansSerif", Font.PLAIN, 11));
		frmContactsV.setBounds(100, 100, 320, 480);
		frmContactsV.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmContactsV.getContentPane().setLayout(null);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, 314, 452);
		frmContactsV.getContentPane().add(mainPanel);
		mainPanel.setLayout(new CardLayout(0, 0));
		
		JPanel contactsPanel = new JPanel();
		mainPanel.add(contactsPanel, "1");
		contactsPanel.setLayout(null);
		
		JPanel newContactPanel = new JPanel();
		mainPanel.add(newContactPanel, "2");
		newContactPanel.setLayout(null);
		
		JPanel contactDetails = new JPanel();
		mainPanel.add(contactDetails, "3");
		contactDetails.setLayout(null);
		
		JPanel editContact = new JPanel();
		mainPanel.add(editContact, "4");
		editContact.setLayout(null);
		
		JPanel groupsPanel = new JPanel();
		mainPanel.add(groupsPanel, "5");
		groupsPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 76, 294, 337);
		groupsPanel.add(scrollPane);
		
		JList<Object> gpList = new JList<Object>();
		scrollPane.setViewportView(gpList);
		gpList.setBackground(UIManager.getColor("Label.background"));
		gpList.setModel(new AbstractListModel<Object>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			String[] values = addressBook.group.getGroupNames(addressBook.group);
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		boolean isChanged = false;
		
		gpList.addMouseListener(new MouseListener() {
			
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
				if (!isChanged) {
					gpList.setModel(new AbstractListModel<Object>() {
						/**
						 * 
						 */
						private static final long serialVersionUID = 1L;
						String[] values = addressBook.group.getGroupMembers(gpList.getSelectedValue().toString() , addressBook.contacts);
						public int getSize() {
							return values.length;
						}
						public Object getElementAt(int index) {
							return values[index];
						}
					});
//					isChanged = true;
				}
			}
		});
		
		JLabel lblGroups = new JLabel("Groups");
		lblGroups.setForeground(Color.DARK_GRAY);
		lblGroups.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblGroups.setBounds(23, 11, 132, 54);
		groupsPanel.add(lblGroups);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout layout = (CardLayout)mainPanel.getLayout();
				layout.show(mainPanel, "1");
				gpList.setModel(new AbstractListModel<Object>() {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;
					String[] values = addressBook.group.getGroupNames(addressBook.group);
					public int getSize() {
						return values.length;
					}
					public Object getElementAt(int index) {
						return values[index];
					}
				});
//				isChanged = false;
			}
		});
		btnBack.setBounds(20, 424, 89, 23);
		groupsPanel.add(btnBack);
		
		JLabel lblNewLabel = new JLabel("New Contact");
		lblNewLabel.setForeground(Color.DARK_GRAY);
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblNewLabel.setBounds(25, 11, 120, 43);
		newContactPanel.add(lblNewLabel);
		
		JLabel lblName = new JLabel("Name");
		lblName.setForeground(Color.DARK_GRAY);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblName.setBounds(25, 86, 35, 17);
		newContactPanel.add(lblName);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number");
		lblPhoneNumber.setForeground(Color.DARK_GRAY);
		lblPhoneNumber.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPhoneNumber.setBounds(25, 114, 92, 17);
		newContactPanel.add(lblPhoneNumber);
		
		JLabel lblBirthday = new JLabel("Birthday");
		lblBirthday.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBirthday.setForeground(Color.DARK_GRAY);
		lblBirthday.setBounds(25, 157, 51, 17);
		newContactPanel.add(lblBirthday);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setForeground(Color.DARK_GRAY);
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAddress.setBounds(25, 185, 48, 17);
		newContactPanel.add(lblAddress);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGender.setForeground(Color.DARK_GRAY);
		lblGender.setBounds(25, 213, 51, 17);
		newContactPanel.add(lblGender);
		
		JLabel lblGroup = new JLabel("Group");
		lblGroup.setForeground(Color.DARK_GRAY);
		lblGroup.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGroup.setBounds(25, 241, 38, 17);
		newContactPanel.add(lblGroup);
		
		JLabel lblEmail = new JLabel("E-Mail");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmail.setForeground(Color.DARK_GRAY);
		lblEmail.setBounds(25, 269, 51, 17);
		newContactPanel.add(lblEmail);
		
		nameField = new JTextField();
		nameField.setBounds(137, 86, 154, 20);
		newContactPanel.add(nameField);
		nameField.setColumns(10);
		
		phoneNumberField = new JTextField();
		phoneNumberField.setBounds(163, 114, 128, 20);
		newContactPanel.add(phoneNumberField);
		phoneNumberField.setColumns(10);
		phoneNumberField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				// phone numbers can have at last 9 digits except 09
				if (!(Character.isDigit(c)) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (phoneNumberField.getText().length() >= 9)) {
					e.consume();
				}
			}
		});
		
		yearField = new JTextField();
		yearField.setBounds(137, 157, 51, 20);
		newContactPanel.add(yearField);
		yearField.setColumns(10);
		yearField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				// at last 4 digits
				if (!(Character.isDigit(c)) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (yearField.getText().length() >= 4)) {
					e.consume();
				}
			}
			
			public void keyReleased(KeyEvent e) {
				try {
					// not greater than 2018
					 if (Integer.parseInt(yearField.getText()) > 2018) {
							yearField.setText(yearField.getText().substring(0, yearField.getText().length() - 1));
						}
				} catch (Exception e2) {
				}
			}
		});
		
		monthField = new JTextField();
		monthField.setBounds(213, 157, 26, 20);
		newContactPanel.add(monthField);
		monthField.setColumns(10);
		monthField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				// not greater than 2 digits
				if (!(Character.isDigit(c)) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (monthField.getText().length() >= 2)) {
					e.consume();
				}
			}
			
			public void keyReleased(KeyEvent e) {
				try {
					// not greater than 12
					 if (Integer.parseInt(monthField.getText()) > 12) {
							monthField.setText(monthField.getText().substring(0, monthField.getText().length() - 1));
						}
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		
		dayField = new JTextField();
		dayField.setColumns(10);
		dayField.setBounds(265, 157, 26, 20);
		newContactPanel.add(dayField);
		dayField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				// last comments
				if (!(Character.isDigit(c)) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (dayField.getText().length() >= 2)) {
					e.consume();
				}
			}

			public void keyReleased(KeyEvent e) {
				try {
					// last comments again -_-
					 if (Integer.parseInt(dayField.getText()) > 31) {
							dayField.setText(dayField.getText().substring(0, dayField.getText().length() - 1));
						}
				} catch (Exception e2) {
				}
			}
		});
		
		addressField = new JTextField();
		addressField.setBounds(137, 185, 154, 20);
		newContactPanel.add(addressField);
		addressField.setColumns(10);
		
		JComboBox<Object> groupCombo = new JComboBox<Object>();
		groupCombo.setModel(new DefaultComboBoxModel<Object>(addressBook.group.getGroups()));
		groupCombo.setBounds(137, 241, 154, 20);
		newContactPanel.add(groupCombo);
		groupCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (groupCombo.getSelectedItem().toString().equals("Other")) {
					newContactPanel.remove(groupCombo);
					newContactPanel.add(groupField);
					newContactPanel.revalidate();
					newContactPanel.repaint();
				}
			}
		});
		
		groupField = new JTextField();
		groupField.setBounds(137, 241, 154, 20);
		groupField.setColumns(10);
		
		emailField = new JTextField();
		emailField.setBounds(137, 269, 154, 20);
		newContactPanel.add(emailField);
		emailField.setColumns(10);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.LIGHT_GRAY);
		separator_1.setBounds(33, 65, 247, 2);
		newContactPanel.add(separator_1);
		
		JLabel label = new JLabel("/");
		label.setForeground(Color.DARK_GRAY);
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(198, 157, 5, 17);
		newContactPanel.add(label);
		
		JLabel label_1 = new JLabel("/");
		label_1.setForeground(Color.DARK_GRAY);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_1.setBounds(249, 157, 5, 17);
		newContactPanel.add(label_1);
		
		// Error massage
		JLabel lblNameAndPhone = new JLabel("Name and Phone Number are requierd fields!");
		lblNameAndPhone.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNameAndPhone.setForeground(Color.RED);
		lblNameAndPhone.setBounds(27, 345, 259, 16);
		
		JLabel lblNoContactsYet = new JLabel("No contacts yet!");
		lblNoContactsYet.setForeground(Color.LIGHT_GRAY);
		lblNoContactsYet.setHorizontalAlignment(SwingConstants.CENTER);
		lblNoContactsYet.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblNoContactsYet.setBounds(72, 72, 169, 70);
		contactsPanel.add(lblNoContactsYet);
		
		JComboBox<Object> genderCombo = new JComboBox<Object>();
		genderCombo.setModel(new DefaultComboBoxModel<Object>(new String[] {"Choose", "Male", "Female"}));
		genderCombo.setBounds(137, 213, 89, 20);
		newContactPanel.add(genderCombo);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.LIGHT_GRAY);
		separator.setBounds(35, 66, 244, 1);
		contactsPanel.add(separator);
		
		JLabel lblContacts = new JLabel("Contacts");
		lblContacts.setForeground(Color.DARK_GRAY);
		lblContacts.setHorizontalAlignment(SwingConstants.LEFT);
		lblContacts.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblContacts.setBounds(32, 11, 149, 44);
		contactsPanel.add(lblContacts);
		
		JButton btnNewContact = new JButton("New Contact");
		btnNewContact.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNewContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout layout = (CardLayout)mainPanel.getLayout();
				layout.show(mainPanel, "2");
			}
		});
		btnNewContact.setBounds(35, 418, 117, 23);
		contactsPanel.add(btnNewContact);
		
		// TODO
		JButton groupsBtn = new JButton("Groups");
		groupsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				groupCombo.setModel(new DefaultComboBoxModel<Object>(addressBook.group.getGroups()));
				CardLayout layout = (CardLayout)mainPanel.getLayout();
				layout.show(mainPanel, "5");
			}
		});
		groupsBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		groupsBtn.setBounds(162, 418, 117, 23);
		contactsPanel.add(groupsBtn);
				
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// tests if name and phone number fields are empty or not
				// if it is Error massage appears
				// if not creates a contact
				if (nameField.getText().isEmpty() || phoneNumberField.getText().isEmpty()) {
					newContactPanel.add(lblNameAndPhone);
					frmContactsV.revalidate();
					frmContactsV.repaint();
				}
				else if (!nameField.getText().isEmpty() && !phoneNumberField.getText().isEmpty()) {
					String name = nameField.getText();
					long phoneNumber = Long.valueOf(phoneNumberField.getText());
					String birthDate = yearField.getText() + "/" + monthField.getText() + "/" + dayField.getText();
					if (birthDate.equals("//")) {
						birthDate = "";
					}
					String address = addressField.getText();
					String gender = "";
					if (!genderCombo.getSelectedItem().toString().equals("Choose")) {
						gender = genderCombo.getSelectedItem().toString();
					}
					String group;
					if (groupCombo.isDisplayable()) {
						group = groupCombo.getSelectedItem().toString();
					}
					else {
						group = groupField.getText();
						addressBook.group.addNewGroup(group);
					}
					String email = emailField.getText();
					
					addressBook.addContact(new Contact(name, phoneNumber, birthDate, address, gender, group, email));
					
					contactsPanel.removeAll();
					contactsPanel.add(separator);
					contactsPanel.add(lblContacts);
					contactsPanel.add(btnNewContact);
					contactsPanel.add(groupsBtn);
					
					addressBook.showContactList(addressBook, contactsPanel, contactDetails, editContact);
					
					frmContactsV.revalidate();
					frmContactsV.repaint();
					
					addressBook.saveObject(addressBook);
					
					CardLayout layout = (CardLayout)mainPanel.getLayout();
					layout.show(mainPanel, "1");
					
					nameField.setText("");
					addressField.setText("");
					groupField.setText("");
					genderCombo.setSelectedIndex(0);
					emailField.setText("");
					phoneNumberField.setText("");
					yearField.setText("");
					monthField.setText("");
					dayField.setText("");
					groupCombo.setModel(new DefaultComboBoxModel<Object>(addressBook.group.getGroups()));
					groupCombo.setSelectedItem(0);
					newContactPanel.remove(groupField);
					newContactPanel.add(groupCombo);
					
					newContactPanel.remove(lblNameAndPhone);
				}
			}
		});
		btnAdd.setBounds(33, 316, 89, 23);
		newContactPanel.add(btnAdd);
		
		// turns back a panel
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout layout = (CardLayout)mainPanel.getLayout();
				layout.show(mainPanel, "1");
				
				nameField.setText("");
				addressField.setText("");
				groupField.setText("");
				genderCombo.setSelectedIndex(0);
				emailField.setText("");
				phoneNumberField.setText("");
				yearField.setText("");
				monthField.setText("");
				dayField.setText("");
				newContactPanel.remove(groupField);
				groupCombo.setModel(new DefaultComboBoxModel<Object>(addressBook.group.getGroups()));
				groupCombo.setSelectedIndex(0);
				newContactPanel.add(groupCombo);
				newContactPanel.remove(lblNameAndPhone);
			}
		});
		btnCancel.setBounds(191, 316, 89, 23);
		newContactPanel.add(btnCancel);
		
		JLabel label_2 = new JLabel("09");
		label_2.setForeground(Color.DARK_GRAY);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_2.setBounds(137, 116, 16, 17);
		newContactPanel.add(label_2);
		
		JLabel lblYear = new JLabel("Year:");
		lblYear.setBounds(162, 142, 41, 14);
		newContactPanel.add(lblYear);
		
		JLabel lblMonth = new JLabel("Month:");
		lblMonth.setBounds(205, 142, 48, 14);
		newContactPanel.add(lblMonth);
		
		JLabel lblDay = new JLabel("Day:");
		lblDay.setBounds(268, 142, 36, 14);
		newContactPanel.add(lblDay);
		
		// making a list of contacts at the first time if there is a saved address book//
		if (addressBook.getCCount() != 0) {
			contactsPanel.remove(lblNoContactsYet);
			addressBook.showContactList(addressBook, contactsPanel, contactDetails, editContact);
			frmContactsV.revalidate();
			frmContactsV.repaint();
		}
	}
}
