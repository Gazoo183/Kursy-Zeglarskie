import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import java.awt.GridLayout;
import java.awt.Component;
import javax.swing.Box;
import java.awt.SystemColor;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.beans.PropertyChangeListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.beans.PropertyChangeEvent;
import javax.swing.JTextField;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.SpringLayout;

import java.time.Period;
import javax.swing.JFormattedTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class Home {

	private JFrame frame;
	private JTable KursTable;
	private JTextField imieTF;
	private JTextField nazwiskoTF;
	private JTextField skanDTTF;
	private UtilDateModel model;
	private JDatePickerImpl datePicker;
	private JComboBox comboBox;
	private JTextField imieTF1;
	private JTextField nazwiskoTF1;
	private JTextField skanDOTF;
	private JTextField numerTelefonuTF;
	
	private JDatePickerImpl datePicker1;
	private UtilDateModel model1;
	private JButton abortButton3;
	private CardLayout profileCP;
	private JPanel ProfileCardPanel;
	private CardLayout cl;
	private Login loginDialog;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private DefaultTableModel dtm2;
	private JScrollPane scrollPane;
	private JButton btnNewButton;
	private InfoDialog infoDialog;
	
	public Home() throws ClassNotFoundException {
		initialize(this);
	}
	
	/*
	 * Wypełnianie/Czyszczenie profili
	 */
	
	public void fillKP(Kursant dane) throws ClassNotFoundException {
		textField.setText(dane.getImie());
		textField_1.setText(dane.getNazwisko());
		textField_2.setText(dane.getDataUrodzenia().toString());
		textField_3.setText(dane.inProgress().getLokalizacja().getNazwa());
		textField_4.setText(dane.inProgress().getDataRozpoczecia().toString());
		textField_5.setText(dane.inProgress().getDataZakonczenia().toString());
		dtm2=new DefaultTableModel(dane.getDataForTable(), new String[] {Wynik.class.getSimpleName()});
		table=new JTable(dtm2);
		scrollPane.setViewportView(table);
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	            if(table.getSelectedRowCount()==1)
	            	btnNewButton.setEnabled(true);
	            else
	            	btnNewButton.setEnabled(false);
	        }
	    });
		
	}
	public void clearKP() {
		textField.setText("");
		textField_1.setText("");
		textField_2.setText("");
		textField_3.setText("");
		textField_4.setText("");
		textField_5.setText("");
		dtm2=new DefaultTableModel(new String[] {Wynik.class.getSimpleName()}, 0);
		table=new JTable(dtm2);
		scrollPane.setViewportView(table);
		btnNewButton.setEnabled(false);
	}
	
	public void fillOP(Opiekun dane) {
		
	}
	public void clearOP() {
		
	}
	
	public void fillIP(Instruktor dane) {
		
	}
	public void clearIP() {
		
	}

	private void initialize(Home home) throws ClassNotFoundException {
		frame = new JFrame();
		frame.setTitle("Kursy żeglarskie");
		frame.setBounds(100, 100, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JDialog ap=new JDialog(frame);
		JPanel apContentPanel = new JPanel();
		ap.setModal(true);
		ap.setResizable(false);
		ap.setTitle("Obowiązek zapłaty");
		ap.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		ap.setAlwaysOnTop(true);
		ap.setVisible(false);
		ap.setBounds(100, 100, 450, 300);
		ap.getContentPane().setLayout(new BorderLayout());
		apContentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		ap.getContentPane().add(apContentPanel, BorderLayout.CENTER);
		apContentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel buttonPane = new JPanel();
			apContentPanel.add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			{
				JButton okButton = new JButton("Akceptuj");
				okButton.setBackground(SystemColor.controlHighlight);
				okButton.setActionCommand("Akceptuj");
				buttonPane.add(okButton);
				ap.getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int tmpid=0;
						
						try {
							tmpid=((ArrayList)ObjectPlus.getExtent(Kursant.class)).size()+1;
						} catch (ClassNotFoundException e2) {
							e2.printStackTrace();
						}
				
						Kursant tmp=new Kursant("k"+tmpid ,"k"+tmpid, imieTF.getText(), nazwiskoTF.getText(), ((Date)datePicker.getModel().getValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), (Poziom)comboBox.getSelectedItem(), skanDTTF.getText(), (Kurs)KursTable.getModel().getValueAt(KursTable.getSelectedRow(), 0));
			
						if(imieTF1.getText().length()!=0) {
							Opiekun tmp1=new Opiekun("o"+tmpid, "o"+tmpid, imieTF1.getText(), nazwiskoTF1.getText(), ((Date)datePicker.getModel().getValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), numerTelefonuTF.getText(), skanDOTF.getText(), tmp);
						}
						
				 		try {
							ObjectOutputStream out=new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("extentFile.txt")));
							ObjectPlus.writeExtents(out);
							out.close();
						} catch (FileNotFoundException e1) {} catch (IOException e1) {} catch (Exception e1) {}
						
						ap.setVisible(false);
						
						new SuccessfulRegistration(tmp).setVisible(true);
						
						abortButton3.getActionListeners()[0].actionPerformed(e);
					}
				});
			}
			{
				Component horizontalStrut = Box.createHorizontalStrut(20);
				buttonPane.add(horizontalStrut);
			}
			{
				JButton cancelButton = new JButton("Odrzuć");
				cancelButton.setBackground(SystemColor.controlHighlight);
				cancelButton.setActionCommand("Odrzuć");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						abortButton3.getActionListeners()[0].actionPerformed(e);
						ap.setVisible(false);
					}
				});
			}
		}
		{
			Box verticalBox = Box.createVerticalBox();
			apContentPanel.add(verticalBox, BorderLayout.CENTER);
			{
				JLabel lblNewLabel = new JLabel("Wciskając przycisk \"Akceptuj\" zobowiązujesz się ");
				lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
				verticalBox.add(lblNewLabel);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("do zapłaty za wybrany kurs");
				lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
				verticalBox.add(lblNewLabel_1);
			}
		}
		{
			Component verticalStrut = Box.createVerticalStrut(71);
			apContentPanel.add(verticalStrut, BorderLayout.NORTH);
		}
		{
			Component horizontalStrut = Box.createHorizontalStrut(57);
			apContentPanel.add(horizontalStrut, BorderLayout.WEST);
		}
		
		FormNotFilled fnf=new FormNotFilled(frame);
		
		JPanel HeaderPanel = new JPanel();
		HeaderPanel.setBackground(Color.GRAY);
		frame.getContentPane().add(HeaderPanel, BorderLayout.NORTH);
		HeaderPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel headerLabel = new JLabel("KURSY ŻEGLARSKIE");
		HeaderPanel.add(headerLabel);
		headerLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
		headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel ContentPanel = new JPanel();
		ContentPanel.setBackground(Color.WHITE);
		frame.getContentPane().add(ContentPanel, BorderLayout.CENTER);
		cl=new CardLayout(0, 0);
		ContentPanel.setLayout(cl);
		
		JPanel HomeContentPanel = new JPanel();
		ContentPanel.add(HomeContentPanel, "name_1673481781792900");
		HomeContentPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel MenuPanel = new JPanel();
		HomeContentPanel.add(MenuPanel, BorderLayout.WEST);
		MenuPanel.setBackground(Color.LIGHT_GRAY);
		MenuPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel MenuLabelPanel = new JPanel();
		MenuLabelPanel.setBackground(Color.LIGHT_GRAY);
		MenuPanel.add(MenuLabelPanel);
		MenuLabelPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel menuLabel = new JLabel("MENU");
		MenuLabelPanel.add(menuLabel, BorderLayout.CENTER);
		menuLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		menuLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton signupButton = new JButton("Zapisz się na kurs");
		signupButton.setBackground(SystemColor.controlHighlight);
		signupButton.setFont(new Font("Tahoma", Font.PLAIN, 19));
		signupButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cl.show(ContentPanel, "name_1674158155582600");
			}
		});
		MenuPanel.add(signupButton);
		
		
		JButton checkResultsButton = new JButton("Sprawdz wyniki");
		checkResultsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginDialog=new Login(home, frame, profileCP, ProfileCardPanel, cl);
				loginDialog.setVisible(true);
			}
		});
		checkResultsButton.setBackground(SystemColor.controlHighlight);
		checkResultsButton.setFont(new Font("Tahoma", Font.PLAIN, 19));
		MenuPanel.add(checkResultsButton);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		MenuPanel.add(verticalStrut);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		MenuPanel.add(verticalStrut_1);
		
		Component verticalStrut_3 = Box.createVerticalStrut(20);
		MenuPanel.add(verticalStrut_3);
		
		Component verticalStrut_2 = Box.createVerticalStrut(20);
		MenuPanel.add(verticalStrut_2);
		
		JPanel KursantFormContentPanel = new JPanel();
		KursantFormContentPanel.setBackground(Color.WHITE);
		ContentPanel.add(KursantFormContentPanel, "name_1674296072170600");
		KursantFormContentPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel KFContentPanel = new JPanel();
		KFContentPanel.setBackground(Color.LIGHT_GRAY);
		KursantFormContentPanel.add(KFContentPanel, BorderLayout.CENTER);
		KFContentPanel.setLayout(new BorderLayout(0, 0));
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(180);
		KFContentPanel.add(horizontalStrut_3, BorderLayout.WEST);
		
		Component horizontalStrut_4 = Box.createHorizontalStrut(180);
		KFContentPanel.add(horizontalStrut_4, BorderLayout.EAST);
		
		JPanel KursantFormPanel = new JPanel();
		KursantFormPanel.setBackground(Color.LIGHT_GRAY);
		KFContentPanel.add(KursantFormPanel, BorderLayout.CENTER);
		KursantFormPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel KursantFormButtonPanel = new JPanel();
		KursantFormButtonPanel.setBackground(Color.LIGHT_GRAY);
		KursantFormPanel.add(KursantFormButtonPanel, BorderLayout.SOUTH);
		KursantFormButtonPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel leftPanel = new JPanel();
		leftPanel.setBackground(Color.LIGHT_GRAY);
		KursantFormButtonPanel.add(leftPanel, BorderLayout.WEST);
		
		JButton backButton = new JButton("Cofnij");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imieTF.setText("");
				nazwiskoTF.setText("");
				datePicker.getModel().setValue(null);
				comboBox.setSelectedIndex(0);
				skanDTTF.setText("");
				
				cl.show(ContentPanel, "name_1674158155582600");
			}
		});
		leftPanel.add(backButton);
		backButton.setBackground(SystemColor.controlHighlight);
		
		JPanel rightPanel = new JPanel();
		rightPanel.setBackground(Color.LIGHT_GRAY);
		KursantFormButtonPanel.add(rightPanel, BorderLayout.EAST);
		
		JDialog obowiazekZaplaty=new JDialog(frame);
		obowiazekZaplaty.setVisible(false);
		
		JButton nextButton = new JButton("Dalej");
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!(imieTF.getText().length()==0) && !(nazwiskoTF.getText().length()==0) && !(datePicker.getModel().getValue()==null) && !(skanDTTF.getText().length()==0)) {
					Period age=Period.between(((Date)datePicker.getModel().getValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now());
					if(age.getYears()<18)
						cl.show(ContentPanel, "name_1674346110319300");
					else
						ap.setVisible(true);
				} else {
					fnf.setVisible(true);
				}
			}
		});
		rightPanel.add(nextButton);
		nextButton.setBackground(SystemColor.controlHighlight);
		
		Component horizontalStrut_5 = Box.createHorizontalStrut(20);
		rightPanel.add(horizontalStrut_5);
		
		JButton abortButton1 = new JButton("Anuluj");
		abortButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imieTF.setText("");
				nazwiskoTF.setText("");
				datePicker.getModel().setValue(null);
				comboBox.setSelectedIndex(0);
				skanDTTF.setText("");
				
				KursTable.clearSelection();
				
				cl.show(ContentPanel, "name_1673481781792900");
			}
		});
		rightPanel.add(abortButton1);
		abortButton1.setBackground(SystemColor.controlHighlight);
		
		JPanel KFPanel = new JPanel();
		KursantFormPanel.add(KFPanel, BorderLayout.CENTER);
		KFPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel kursantForm = new JPanel();
		KFPanel.add(kursantForm, BorderLayout.CENTER);
		
		model=new UtilDateModel();
		JDatePanelImpl datePanel=new JDatePanelImpl(model);
		kursantForm.setLayout(new GridLayout(0, 3, 0, 0));
		
		Component horizontalStrut_49 = Box.createHorizontalStrut(20);
		kursantForm.add(horizontalStrut_49);
		
		Component horizontalStrut_45 = Box.createHorizontalStrut(20);
		kursantForm.add(horizontalStrut_45);
		
		Component horizontalStrut_44 = Box.createHorizontalStrut(20);
		kursantForm.add(horizontalStrut_44);
		
		Component horizontalStrut_40 = Box.createHorizontalStrut(20);
		kursantForm.add(horizontalStrut_40);
		
		Component horizontalStrut_39 = Box.createHorizontalStrut(20);
		kursantForm.add(horizontalStrut_39);
		
		Component horizontalStrut_38 = Box.createHorizontalStrut(20);
		kursantForm.add(horizontalStrut_38);
		
		Component horizontalStrut_6 = Box.createHorizontalStrut(20);
		kursantForm.add(horizontalStrut_6);
		
		JLabel imieL = new JLabel("Imie:");
		imieL.setHorizontalAlignment(SwingConstants.CENTER);
		kursantForm.add(imieL);
		
		Component horizontalStrut_7 = Box.createHorizontalStrut(20);
		kursantForm.add(horizontalStrut_7);
		
		Component horizontalStrut_8 = Box.createHorizontalStrut(20);
		kursantForm.add(horizontalStrut_8);
		
		imieTF = new JTextField();
		kursantForm.add(imieTF);
		imieTF.setColumns(15);
		
		Component horizontalStrut_9 = Box.createHorizontalStrut(20);
		kursantForm.add(horizontalStrut_9);
		
		Component horizontalStrut_35 = Box.createHorizontalStrut(20);
		kursantForm.add(horizontalStrut_35);
		
		Component horizontalStrut_36 = Box.createHorizontalStrut(20);
		kursantForm.add(horizontalStrut_36);
		
		Component horizontalStrut_37 = Box.createHorizontalStrut(20);
		kursantForm.add(horizontalStrut_37);
		
		Component horizontalStrut_10 = Box.createHorizontalStrut(20);
		kursantForm.add(horizontalStrut_10);
		
		JLabel nazwiskoL = new JLabel("Nazwisko:");
		nazwiskoL.setHorizontalAlignment(SwingConstants.CENTER);
		kursantForm.add(nazwiskoL);
		
		Component horizontalStrut_11 = Box.createHorizontalStrut(20);
		kursantForm.add(horizontalStrut_11);
		
		Component horizontalStrut_12 = Box.createHorizontalStrut(20);
		kursantForm.add(horizontalStrut_12);
		
		nazwiskoTF = new JTextField();
		kursantForm.add(nazwiskoTF);
		nazwiskoTF.setColumns(15);
		
		Component horizontalStrut_13 = Box.createHorizontalStrut(20);
		kursantForm.add(horizontalStrut_13);
		
		Component horizontalStrut_26 = Box.createHorizontalStrut(20);
		kursantForm.add(horizontalStrut_26);
		
		Component horizontalStrut_27 = Box.createHorizontalStrut(20);
		kursantForm.add(horizontalStrut_27);
		
		Component horizontalStrut_28 = Box.createHorizontalStrut(20);
		kursantForm.add(horizontalStrut_28);
		
		Component horizontalStrut_14 = Box.createHorizontalStrut(20);
		kursantForm.add(horizontalStrut_14);
		
		JLabel lblDataUrodzenia = new JLabel("Data urodzenia:");
		lblDataUrodzenia.setHorizontalAlignment(SwingConstants.CENTER);
		kursantForm.add(lblDataUrodzenia);
		
		Component horizontalStrut_15 = Box.createHorizontalStrut(20);
		kursantForm.add(horizontalStrut_15);
		
		Component horizontalStrut_17 = Box.createHorizontalStrut(20);
		kursantForm.add(horizontalStrut_17);
		datePicker=new JDatePickerImpl(datePanel);
		SpringLayout springLayout = (SpringLayout) datePicker.getLayout();
		springLayout.putConstraint(SpringLayout.SOUTH, datePicker.getJFormattedTextField(), 0, SpringLayout.SOUTH, datePicker);
		datePicker.getJFormattedTextField().setBackground(SystemColor.controlHighlight);
		datePicker.getJFormattedTextField().setHorizontalAlignment(SwingConstants.CENTER);
		datePicker.setBackground(SystemColor.controlHighlight);
		kursantForm.add(datePicker);
		
		Component horizontalStrut_16 = Box.createHorizontalStrut(20);
		kursantForm.add(horizontalStrut_16);
		
		Component horizontalStrut_29 = Box.createHorizontalStrut(20);
		kursantForm.add(horizontalStrut_29);
		
		Component horizontalStrut_30 = Box.createHorizontalStrut(20);
		kursantForm.add(horizontalStrut_30);
		
		Component horizontalStrut_31 = Box.createHorizontalStrut(20);
		kursantForm.add(horizontalStrut_31);
		
		Component horizontalStrut_18 = Box.createHorizontalStrut(20);
		kursantForm.add(horizontalStrut_18);
		
		JLabel lblPoziomUmiejtnoci = new JLabel("Poziom umiejętności:");
		lblPoziomUmiejtnoci.setHorizontalAlignment(SwingConstants.CENTER);
		kursantForm.add(lblPoziomUmiejtnoci);
		
		Component horizontalStrut_19 = Box.createHorizontalStrut(20);
		kursantForm.add(horizontalStrut_19);
		
		Component horizontalStrut_20 = Box.createHorizontalStrut(20);
		kursantForm.add(horizontalStrut_20);
		
		comboBox = new JComboBox();
		comboBox.setBackground(SystemColor.controlHighlight);
		comboBox.setMaximumRowCount(3);
		comboBox.addItem(Poziom.NOWICJUSZ);
		comboBox.addItem(Poziom.SZKOLONY);
		comboBox.addItem(Poziom.LICENCJONOWANY);
		kursantForm.add(comboBox);
		
		Component horizontalStrut_32 = Box.createHorizontalStrut(20);
		kursantForm.add(horizontalStrut_32);
		
		Component horizontalStrut_33 = Box.createHorizontalStrut(20);
		kursantForm.add(horizontalStrut_33);
		
		Component horizontalStrut_34 = Box.createHorizontalStrut(20);
		kursantForm.add(horizontalStrut_34);
		
		Component horizontalStrut_21 = Box.createHorizontalStrut(20);
		kursantForm.add(horizontalStrut_21);
		
		Component horizontalStrut_22 = Box.createHorizontalStrut(20);
		kursantForm.add(horizontalStrut_22);
		
		JLabel lblSkanDowoduTosamoci = new JLabel("Skan dowodu tożsamości:");
		lblSkanDowoduTosamoci.setHorizontalAlignment(SwingConstants.CENTER);
		kursantForm.add(lblSkanDowoduTosamoci);
		
		Component horizontalStrut_23 = Box.createHorizontalStrut(20);
		kursantForm.add(horizontalStrut_23);
		
		Component horizontalStrut_24 = Box.createHorizontalStrut(20);
		kursantForm.add(horizontalStrut_24);
		
		skanDTTF = new JTextField();
		kursantForm.add(skanDTTF);
		skanDTTF.setColumns(10);
		
		Component horizontalStrut_25 = Box.createHorizontalStrut(20);
		kursantForm.add(horizontalStrut_25);
		
		Component horizontalStrut_41 = Box.createHorizontalStrut(20);
		kursantForm.add(horizontalStrut_41);
		
		Component horizontalStrut_42 = Box.createHorizontalStrut(20);
		kursantForm.add(horizontalStrut_42);
		
		Component horizontalStrut_43 = Box.createHorizontalStrut(20);
		kursantForm.add(horizontalStrut_43);
		
		Component horizontalStrut_46 = Box.createHorizontalStrut(20);
		kursantForm.add(horizontalStrut_46);
		
		Component horizontalStrut_47 = Box.createHorizontalStrut(20);
		kursantForm.add(horizontalStrut_47);
		
		Component horizontalStrut_48 = Box.createHorizontalStrut(20);
		kursantForm.add(horizontalStrut_48);
		
		JLabel ZarejestrujKursantaLabel = new JLabel("Zarejestruj kursanta");
		KursantFormPanel.add(ZarejestrujKursantaLabel, BorderLayout.NORTH);
		ZarejestrujKursantaLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		ZarejestrujKursantaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel OpiekunFormContentPanel = new JPanel();
		OpiekunFormContentPanel.setBackground(Color.WHITE);
		ContentPanel.add(OpiekunFormContentPanel, "name_1674346110319300");
		OpiekunFormContentPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel OFContentPanel = new JPanel();
		OFContentPanel.setBackground(Color.LIGHT_GRAY);
		OpiekunFormContentPanel.add(OFContentPanel, BorderLayout.CENTER);
		OFContentPanel.setLayout(new BorderLayout(0, 0));
		
		Component horizontalStrut_50 = Box.createHorizontalStrut(180);
		OFContentPanel.add(horizontalStrut_50, BorderLayout.WEST);
		
		Component horizontalStrut_51 = Box.createHorizontalStrut(180);
		OFContentPanel.add(horizontalStrut_51, BorderLayout.EAST);
		
		JPanel OpiekunFormPanel = new JPanel();
		OpiekunFormPanel.setBackground(Color.LIGHT_GRAY);
		OFContentPanel.add(OpiekunFormPanel, BorderLayout.CENTER);
		OpiekunFormPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel OpiekunFormButtonPanel = new JPanel();
		OpiekunFormButtonPanel.setBackground(Color.LIGHT_GRAY);
		OpiekunFormPanel.add(OpiekunFormButtonPanel, BorderLayout.SOUTH);
		OpiekunFormButtonPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel leftPanel1 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) leftPanel1.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		leftPanel1.setBackground(Color.LIGHT_GRAY);
		OpiekunFormButtonPanel.add(leftPanel1, BorderLayout.WEST);
		
		JButton backButton1 = new JButton("Cofnij");
		backButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				imieTF1.setText("");
				nazwiskoTF1.setText("");
				datePicker1.getModel().setValue(null);
				numerTelefonuTF.setText("");
				skanDOTF.setText("");
				
				cl.show(ContentPanel, "name_1674296072170600");
			}
		});
		backButton1.setBackground(SystemColor.controlHighlight);
		leftPanel1.add(backButton1);
		
		JPanel rightPanel1 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) rightPanel1.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		rightPanel1.setBackground(Color.LIGHT_GRAY);
		OpiekunFormButtonPanel.add(rightPanel1, BorderLayout.EAST);
		
		abortButton3 = new JButton("Anuluj");
		abortButton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imieTF1.setText("");
				nazwiskoTF1.setText("");
				datePicker1.getModel().setValue(null);
				numerTelefonuTF.setText("");
				skanDOTF.setText("");
				
				imieTF.setText("");
				nazwiskoTF.setText("");
				datePicker.getModel().setValue(null);
				comboBox.setSelectedIndex(0);
				skanDTTF.setText("");
				
				KursTable.clearSelection();
				
				cl.show(ContentPanel, "name_1673481781792900");
			}
		});
		abortButton3.setBackground(SystemColor.controlHighlight);
		
		JButton nextButton2 = new JButton("Dalej");
		nextButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!(imieTF1.getText().length()==0) && !(nazwiskoTF1.getText().length()==0) && !(datePicker1.getModel().getValue()==null) && !(numerTelefonuTF.getText().length()==0) && !(skanDOTF.getText().length()==0)) {
					Period age=Period.between(((Date)datePicker1.getModel().getValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now());
					if(age.getYears()<18) {
						abortButton3.getActionListeners()[0].actionPerformed(e);
						new FormError(frame, "opiekun musi być pełnoletni").setVisible(true);
					} else
					ap.setVisible(true);
				} else {
					fnf.setVisible(true);
				}
			}
		});
		
		nextButton2.setBackground(SystemColor.controlHighlight);
		rightPanel1.add(nextButton2);
		
		Component horizontalStrut_52 = Box.createHorizontalStrut(20);
		rightPanel1.add(horizontalStrut_52);
		
		rightPanel1.add(abortButton3);
		
		JLabel ZarejestrujOpiekunaLabel = new JLabel("Zarejestruj opiekuna");
		ZarejestrujOpiekunaLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		ZarejestrujOpiekunaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		OpiekunFormPanel.add(ZarejestrujOpiekunaLabel, BorderLayout.NORTH);
		
		JPanel OFPanel = new JPanel();
		OpiekunFormPanel.add(OFPanel, BorderLayout.CENTER);
		OFPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel opiekunForm = new JPanel();
		OFPanel.add(opiekunForm, BorderLayout.CENTER);
		opiekunForm.setLayout(new GridLayout(0, 3, 0, 0));
		
		Component horizontalStrut_59 = Box.createHorizontalStrut(20);
		opiekunForm.add(horizontalStrut_59);
		
		Component horizontalStrut_58 = Box.createHorizontalStrut(20);
		opiekunForm.add(horizontalStrut_58);
		
		Component horizontalStrut_57 = Box.createHorizontalStrut(20);
		opiekunForm.add(horizontalStrut_57);
		
		Component horizontalStrut_68 = Box.createHorizontalStrut(20);
		opiekunForm.add(horizontalStrut_68);
		
		Component horizontalStrut_67 = Box.createHorizontalStrut(20);
		opiekunForm.add(horizontalStrut_67);
		
		Component horizontalStrut_63 = Box.createHorizontalStrut(20);
		opiekunForm.add(horizontalStrut_63);
		
		Component horizontalStrut_77 = Box.createHorizontalStrut(20);
		opiekunForm.add(horizontalStrut_77);
		
		JLabel imieL1 = new JLabel("Imie:");
		imieL1.setHorizontalAlignment(SwingConstants.CENTER);
		opiekunForm.add(imieL1);
		
		Component horizontalStrut_76 = Box.createHorizontalStrut(20);
		opiekunForm.add(horizontalStrut_76);
		
		Component horizontalStrut_75 = Box.createHorizontalStrut(20);
		opiekunForm.add(horizontalStrut_75);
		
		imieTF1 = new JTextField();
		opiekunForm.add(imieTF1);
		imieTF1.setColumns(10);
		
		Component horizontalStrut_92 = Box.createHorizontalStrut(20);
		opiekunForm.add(horizontalStrut_92);
		
		Component horizontalStrut_90 = Box.createHorizontalStrut(20);
		opiekunForm.add(horizontalStrut_90);
		
		Component horizontalStrut_91 = Box.createHorizontalStrut(20);
		opiekunForm.add(horizontalStrut_91);
		
		Component horizontalStrut_95 = Box.createHorizontalStrut(20);
		opiekunForm.add(horizontalStrut_95);
		
		Component horizontalStrut_94 = Box.createHorizontalStrut(20);
		opiekunForm.add(horizontalStrut_94);
		
		JLabel nazwiskoL1 = new JLabel("Nazwisko:");
		nazwiskoL1.setHorizontalAlignment(SwingConstants.CENTER);
		opiekunForm.add(nazwiskoL1);
		
		Component horizontalStrut_93 = Box.createHorizontalStrut(20);
		opiekunForm.add(horizontalStrut_93);
		
		Component horizontalStrut_89 = Box.createHorizontalStrut(20);
		opiekunForm.add(horizontalStrut_89);
		
		nazwiskoTF1 = new JTextField();
		opiekunForm.add(nazwiskoTF1);
		nazwiskoTF1.setColumns(10);
		
		Component horizontalStrut_96 = Box.createHorizontalStrut(20);
		opiekunForm.add(horizontalStrut_96);
		
		Component horizontalStrut_88 = Box.createHorizontalStrut(20);
		opiekunForm.add(horizontalStrut_88);
		
		Component horizontalStrut_87 = Box.createHorizontalStrut(20);
		opiekunForm.add(horizontalStrut_87);
		
		Component horizontalStrut_86 = Box.createHorizontalStrut(20);
		opiekunForm.add(horizontalStrut_86);
		
		Component horizontalStrut_83 = Box.createHorizontalStrut(20);
		opiekunForm.add(horizontalStrut_83);
		
		JLabel dataUrodzeniaL1 = new JLabel("Data urodzenia:");
		dataUrodzeniaL1.setHorizontalAlignment(SwingConstants.CENTER);
		opiekunForm.add(dataUrodzeniaL1);
		
		Component horizontalStrut_85 = Box.createHorizontalStrut(20);
		opiekunForm.add(horizontalStrut_85);
		
		Component horizontalStrut_84 = Box.createHorizontalStrut(20);
		opiekunForm.add(horizontalStrut_84);
		
		model1=new UtilDateModel();
		JDatePanelImpl datePanel1=new JDatePanelImpl(model1);
		opiekunForm.setLayout(new GridLayout(0, 3, 0, 0));
		
		datePicker1=new JDatePickerImpl(datePanel1);
		SpringLayout springLayout1 = (SpringLayout) datePicker1.getLayout();
		springLayout1.putConstraint(SpringLayout.SOUTH, datePicker1.getJFormattedTextField(), 0, SpringLayout.SOUTH, datePicker1);
		datePicker1.getJFormattedTextField().setBackground(SystemColor.controlHighlight);
		datePicker1.getJFormattedTextField().setHorizontalAlignment(SwingConstants.CENTER);
		datePicker1.setBackground(SystemColor.controlHighlight);
		opiekunForm.add(datePicker1);
		
		Component horizontalStrut_82 = Box.createHorizontalStrut(20);
		opiekunForm.add(horizontalStrut_82);
		
		Component horizontalStrut_80 = Box.createHorizontalStrut(20);
		opiekunForm.add(horizontalStrut_80);
		
		Component horizontalStrut_79 = Box.createHorizontalStrut(20);
		opiekunForm.add(horizontalStrut_79);
		
		Component horizontalStrut_78 = Box.createHorizontalStrut(20);
		opiekunForm.add(horizontalStrut_78);
		
		Component horizontalStrut_81 = Box.createHorizontalStrut(20);
		opiekunForm.add(horizontalStrut_81);
		
		JLabel numerTelefonuL = new JLabel("Numer telefonu:");
		numerTelefonuL.setHorizontalAlignment(SwingConstants.CENTER);
		opiekunForm.add(numerTelefonuL);
		
		Component horizontalStrut_74 = Box.createHorizontalStrut(20);
		opiekunForm.add(horizontalStrut_74);
		
		Component horizontalStrut_69 = Box.createHorizontalStrut(20);
		opiekunForm.add(horizontalStrut_69);
		
		numerTelefonuTF = new JTextField();
		opiekunForm.add(numerTelefonuTF);
		numerTelefonuTF.setColumns(10);
		
		Component horizontalStrut_73 = Box.createHorizontalStrut(20);
		opiekunForm.add(horizontalStrut_73);
		
		Component horizontalStrut_72 = Box.createHorizontalStrut(20);
		opiekunForm.add(horizontalStrut_72);
		
		Component horizontalStrut_71 = Box.createHorizontalStrut(20);
		opiekunForm.add(horizontalStrut_71);
		
		Component horizontalStrut_70 = Box.createHorizontalStrut(20);
		opiekunForm.add(horizontalStrut_70);
		
		Component horizontalStrut_65 = Box.createHorizontalStrut(20);
		opiekunForm.add(horizontalStrut_65);
		
		JLabel skanDOL = new JLabel("Skan dowodu osobistego:");
		skanDOL.setHorizontalAlignment(SwingConstants.CENTER);
		opiekunForm.add(skanDOL);
		
		Component horizontalStrut_64 = Box.createHorizontalStrut(20);
		opiekunForm.add(horizontalStrut_64);
		
		Component horizontalStrut_62 = Box.createHorizontalStrut(20);
		opiekunForm.add(horizontalStrut_62);
		
		skanDOTF = new JTextField();
		opiekunForm.add(skanDOTF);
		skanDOTF.setColumns(10);
		
		Component horizontalStrut_66 = Box.createHorizontalStrut(20);
		opiekunForm.add(horizontalStrut_66);
		
		Component horizontalStrut_61 = Box.createHorizontalStrut(20);
		opiekunForm.add(horizontalStrut_61);
		
		Component horizontalStrut_60 = Box.createHorizontalStrut(20);
		opiekunForm.add(horizontalStrut_60);
		
		Component horizontalStrut_56 = Box.createHorizontalStrut(20);
		opiekunForm.add(horizontalStrut_56);
		
		Component horizontalStrut_53 = Box.createHorizontalStrut(20);
		opiekunForm.add(horizontalStrut_53);
		
		Component horizontalStrut_55 = Box.createHorizontalStrut(20);
		opiekunForm.add(horizontalStrut_55);
		
		Component horizontalStrut_54 = Box.createHorizontalStrut(20);
		opiekunForm.add(horizontalStrut_54);
		
		JPanel KursTableContentPanel = new JPanel();
		KursTableContentPanel.setBackground(Color.WHITE);
		ContentPanel.add(KursTableContentPanel, "name_1674158155582600");
		KursTableContentPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel KTContentPanel = new JPanel();
		KTContentPanel.setBackground(Color.LIGHT_GRAY);
		KursTableContentPanel.add(KTContentPanel, BorderLayout.CENTER);
		KTContentPanel.setLayout(new BorderLayout(0, 0));
		
		Component horizontalStrut = Box.createHorizontalStrut(180);
		horizontalStrut.setForeground(Color.GRAY);
		horizontalStrut.setBackground(Color.GRAY);
		KTContentPanel.add(horizontalStrut, BorderLayout.WEST);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(180);
		horizontalStrut_1.setForeground(Color.GRAY);
		horizontalStrut_1.setBackground(Color.GRAY);
		KTContentPanel.add(horizontalStrut_1, BorderLayout.EAST);
		
		DefaultTableModel dtm1=new DefaultTableModel(Kurs.getDataForTable(), new String[] {Kurs.class.getSimpleName()});
		
		JPanel KursTablePanel = new JPanel();
		KTContentPanel.add(KursTablePanel, BorderLayout.CENTER);
		KursTablePanel.setLayout(new BorderLayout(0, 0));
		
		JPanel KursTableButtonPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) KursTableButtonPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		KursTableButtonPanel.setBackground(Color.LIGHT_GRAY);
		KursTablePanel.add(KursTableButtonPanel, BorderLayout.SOUTH);
		
		JButton selectButton = new JButton("Wybierz");
		selectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cl.show(ContentPanel, "name_1674296072170600");
			}
		});
		selectButton.setEnabled(false);
		selectButton.setBackground(SystemColor.controlHighlight);
		KursTableButtonPanel.add(selectButton);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		KursTableButtonPanel.add(horizontalStrut_2);
		
		JButton abortButton = new JButton("Anuluj");
		abortButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KursTable.clearSelection();
				cl.show(ContentPanel, "name_1673481781792900");
			}
		});
		abortButton.setBackground(SystemColor.controlHighlight);
		KursTableButtonPanel.add(abortButton);
		
		JScrollPane kursTableScroll = new JScrollPane();
		KursTablePanel.add(kursTableScroll, BorderLayout.CENTER);
		
		KursTable = new JTable(dtm1);
		KursTable.setFont(new Font("Tahoma", Font.PLAIN, 20));
		kursTableScroll.setViewportView(KursTable);
		
		KursTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	            if(KursTable.getSelectedRowCount()==1)
	            	selectButton.setEnabled(true);
	            else
	            	selectButton.setEnabled(false);
	        }
	    });
		
		JLabel wybierzKurs = new JLabel("Wybierz kurs:");
		wybierzKurs.setFont(new Font("Tahoma", Font.BOLD, 20));
		wybierzKurs.setHorizontalAlignment(SwingConstants.CENTER);
		KTContentPanel.add(wybierzKurs, BorderLayout.NORTH);
		
		JPanel ProfileContentPanel = new JPanel();
		ProfileContentPanel.setBackground(Color.LIGHT_GRAY);
		ContentPanel.add(ProfileContentPanel, "name_1820385697516000");
		ProfileContentPanel.setLayout(new BorderLayout(0, 0));
		
		ProfileCardPanel = new JPanel();
		ProfileContentPanel.add(ProfileCardPanel);
		profileCP=new CardLayout(0, 0);
		ProfileCardPanel.setLayout(profileCP);
		
		JPanel KursantProfilePanel = new JPanel();
		KursantProfilePanel.setBackground(Color.LIGHT_GRAY);
		ProfileCardPanel.add(KursantProfilePanel, "name_1821001014070600");
		KursantProfilePanel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblProfilKursanta = new JLabel("Profil kursanta");
		lblProfilKursanta.setBackground(Color.LIGHT_GRAY);
		lblProfilKursanta.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblProfilKursanta.setHorizontalAlignment(SwingConstants.CENTER);
		KursantProfilePanel.add(lblProfilKursanta, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		KursantProfilePanel.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.LIGHT_GRAY);
		panel.add(panel_5, BorderLayout.WEST);
		
		JButton btnMenuGwne = new JButton("Menu główne");
		panel_5.add(btnMenuGwne);
		btnMenuGwne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearKP();
				
				cl.first(ContentPanel);
			}
		});
		btnMenuGwne.setBackground(SystemColor.controlHighlight);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.LIGHT_GRAY);
		panel.add(panel_6, BorderLayout.EAST);
		
		btnNewButton = new JButton("Więcej informacji");
		btnNewButton.setEnabled(false);
		btnNewButton.setBackground(SystemColor.controlHighlight);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				infoDialog=new InfoDialog(btnNewButton.getText(),"Dodatkowe informacje: ", ((Wynik)table.getModel().getValueAt(table.getSelectedRow(), 0)).getEgzamin().toString());
				infoDialog.setVisible(true);
			}
		});
		panel_6.add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		KursantProfilePanel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		Box horizontalBox_3 = Box.createHorizontalBox();
		panel_1.add(horizontalBox_3);
		
		Box verticalBox = Box.createVerticalBox();
		horizontalBox_3.add(verticalBox);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EmptyBorder(10, 10, 10, 5));
		verticalBox.add(panel_2);
		panel_2.setLayout(new GridLayout(6, 0, 0, 0));
		
		JLabel lblNewLabel_5 = new JLabel("Kursant:");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_5);
		
		Box horizontalBox = Box.createHorizontalBox();
		panel_2.add(horizontalBox);
		
		JLabel lblNewLabel_2 = new JLabel("Imie: ");
		horizontalBox.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setEditable(false);
		horizontalBox.add(textField);
		textField.setColumns(10);
		
		Component verticalStrut_4 = Box.createVerticalStrut(20);
		panel_2.add(verticalStrut_4);
		
		Box horizontalBox_1 = Box.createHorizontalBox();
		panel_2.add(horizontalBox_1);
		
		JLabel lblNewLabel_3 = new JLabel("Nazwisko: ");
		horizontalBox_1.add(lblNewLabel_3);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		horizontalBox_1.add(textField_1);
		textField_1.setColumns(10);
		
		Component verticalStrut_5 = Box.createVerticalStrut(20);
		panel_2.add(verticalStrut_5);
		
		Box horizontalBox_2 = Box.createHorizontalBox();
		panel_2.add(horizontalBox_2);
		
		JLabel lblNewLabel_4 = new JLabel("Data urodzenia: ");
		horizontalBox_2.add(lblNewLabel_4);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		horizontalBox_2.add(textField_2);
		textField_2.setColumns(10);
		
		Box verticalBox_1 = Box.createVerticalBox();
		horizontalBox_3.add(verticalBox_1);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new EmptyBorder(10, 5, 10, 10));
		verticalBox_1.add(panel_4);
		panel_4.setLayout(new GridLayout(6, 4, 0, 0));
		
		JLabel lblKurs = new JLabel("Kurs:");
		lblKurs.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblKurs);
		
		Box horizontalBox_5 = Box.createHorizontalBox();
		panel_4.add(horizontalBox_5);
		
		JLabel lblLokalizacja = new JLabel("Lokalizajca: ");
		horizontalBox_5.add(lblLokalizacja);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		horizontalBox_5.add(textField_3);
		textField_3.setColumns(10);
		
		Component verticalStrut_6 = Box.createVerticalStrut(20);
		panel_4.add(verticalStrut_6);
		
		Box horizontalBox_6 = Box.createHorizontalBox();
		panel_4.add(horizontalBox_6);
		
		JLabel lblRozpoczcie = new JLabel("Rozpoczęcie: ");
		horizontalBox_6.add(lblRozpoczcie);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		horizontalBox_6.add(textField_4);
		textField_4.setColumns(10);
		
		Component verticalStrut_7 = Box.createVerticalStrut(20);
		panel_4.add(verticalStrut_7);
		
		Box horizontalBox_4 = Box.createHorizontalBox();
		panel_4.add(horizontalBox_4);
		
		JLabel lblZakoczenie = new JLabel("Zakończenie: ");
		horizontalBox_4.add(lblZakoczenie);
		
		textField_5 = new JTextField();
		textField_5.setEditable(false);
		horizontalBox_4.add(textField_5);
		textField_5.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new EmptyBorder(0, 10, 10, 10));
		panel_1.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel_3.add(scrollPane);
		
		dtm2=new DefaultTableModel(new String[] {Wynik.class.getSimpleName()}, 0);
		table = new JTable(dtm2);
		scrollPane.setViewportView(table);
		
		JPanel OpiekunProfilePanel = new JPanel();
		OpiekunProfilePanel.setBackground(Color.LIGHT_GRAY);
		ProfileCardPanel.add(OpiekunProfilePanel, "name_1821016463951800");
		OpiekunProfilePanel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblProfilOpiekuna = new JLabel("Profil opiekuna");
		lblProfilOpiekuna.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblProfilOpiekuna.setHorizontalAlignment(SwingConstants.CENTER);
		OpiekunProfilePanel.add(lblProfilOpiekuna, BorderLayout.NORTH);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(Color.LIGHT_GRAY);
		OpiekunProfilePanel.add(panel_7, BorderLayout.SOUTH);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_8 = new JPanel();
		panel_8.setBackground(Color.LIGHT_GRAY);
		panel_7.add(panel_8, BorderLayout.WEST);
		
		JButton btnMenuGwn = new JButton("Menu główne");
		btnMenuGwn.setBackground(SystemColor.controlHighlight);
		panel_8.add(btnMenuGwn);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBackground(Color.LIGHT_GRAY);
		panel_7.add(panel_9, BorderLayout.EAST);
		
		JButton btnNewButton_1 = new JButton("Więcej informacji");
		btnNewButton_1.setEnabled(false);
		btnNewButton_1.setBackground(SystemColor.controlHighlight);
		panel_9.add(btnNewButton_1);
		
		JPanel panel_10 = new JPanel();
		OpiekunProfilePanel.add(panel_10, BorderLayout.CENTER);
		
		JPanel InstruktorProfilePanel = new JPanel();
		ProfileCardPanel.add(InstruktorProfilePanel, "name_1821020719913100");
		
		Component horizontalStrut_97 = Box.createHorizontalStrut(180);
		horizontalStrut_97.setForeground(Color.LIGHT_GRAY);
		horizontalStrut_97.setBackground(Color.LIGHT_GRAY);
		ProfileContentPanel.add(horizontalStrut_97, BorderLayout.WEST);
		
		Component horizontalStrut_98 = Box.createHorizontalStrut(180);
		horizontalStrut_98.setForeground(Color.LIGHT_GRAY);
		horizontalStrut_98.setBackground(Color.LIGHT_GRAY);
		ProfileContentPanel.add(horizontalStrut_98, BorderLayout.EAST);
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
}
