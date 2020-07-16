import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class FormError extends JDialog {
	private JPanel contentPanel = new JPanel();
	/**
	 * Create the dialog.
	 */
	public FormError(Frame owner, String message) {
		super(owner);
		setModal(true);
		setResizable(false);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(false);
		this.setAlwaysOnTop(true);
		setTitle("Error");
		setBounds(100, 100, 477, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JButton okButton = new JButton("OK");
			okButton.setBackground(SystemColor.controlHighlight);
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisibility(false);
				}
			});
			contentPanel.add(okButton, BorderLayout.SOUTH);
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		{
			JLabel fillMessage = new JLabel("Błąd: "+message);
			fillMessage.setForeground(Color.RED);
			fillMessage.setFont(new Font("Tahoma", Font.BOLD, 25));
			fillMessage.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(fillMessage, BorderLayout.CENTER);
		}
	}
	
	public void setVisibility(boolean bool) {
		this.setVisible(bool);
	}
}
