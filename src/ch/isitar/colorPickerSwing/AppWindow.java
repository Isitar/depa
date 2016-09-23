package ch.isitar.colorPickerSwing;

import java.awt.EventQueue;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

import java.awt.Color;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import java.awt.event.AdjustmentListener;
import java.awt.event.AdjustmentEvent;

public class AppWindow implements Observer {
	private CurrentColor currColor = new CurrentColor();

	private JFrame frame;
	private JTextField txtRed;
	private JTextField txtGreen;
	private JTextField txtBlue;

	private JScrollBar scRed;
	private JScrollBar scBlue;
	private JScrollBar scGreen;
	private JLabel lblRed;
	private JLabel lblGreen;
	private JLabel lblBlue;
	private JButton btDarker;
	private JButton btLighter;
	private JPanel pnPreview;
	private JPanel pnRadioButtons;
	JButton btnExit;

	private boolean ignoreChanges = false;
	private JMenuItem btClear;
	private JMenuItem btAdd;

	
	private ArrayList<Color> savedColors = new ArrayList<>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppWindow window = new AppWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AppWindow() {
		initialize();
		listeners();
		currColor.addObserver(this);
	}

	private void listeners() {
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

		scRed.addAdjustmentListener(new AdjustmentListener() {
			public void adjustmentValueChanged(AdjustmentEvent arg0) {
				if (!ignoreChanges)
					currColor.setRed(arg0.getValue());
			}
		});
		scGreen.addAdjustmentListener(new AdjustmentListener() {
			public void adjustmentValueChanged(AdjustmentEvent arg0) {
				if (!ignoreChanges)
					currColor.setGreen(arg0.getValue());
			}
		});
		scBlue.addAdjustmentListener(new AdjustmentListener() {
			public void adjustmentValueChanged(AdjustmentEvent arg0) {
				if (!ignoreChanges)
					currColor.setBlue(arg0.getValue());
			}
		});

		txtRed.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!ignoreChanges)
					currColor.setRed(Integer.parseInt(((JTextField) e.getSource()).getText()));
			}
		});
		txtGreen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!ignoreChanges)
					currColor.setGreen(Integer.parseInt(((JTextField) e.getSource()).getText()));
			}
		});
		txtBlue.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!ignoreChanges)
					currColor.setBlue(Integer.parseInt(((JTextField) e.getSource()).getText()));
			}
		});

		btDarker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				currColor.darker();
			}
		});

		btLighter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currColor.brighter();
			}
		});
		
		btAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				
			}
		});
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 396, 250);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		btnExit = new JButton("Exit");

		mnFile.add(btnExit);

		JMenu mnAttributes = new JMenu("Attributes");
		menuBar.add(mnAttributes);

		btClear = new JMenuItem("Clear");
		mnAttributes.add(btClear);

		btAdd = new JMenuItem("Add");
		mnAttributes.add(btAdd);
		frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0 };
		gbl_panel.columnWeights = new double[] { 1.0, 1.0, 1.0, 0.0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_panel.columnWidths = new int[] { 100, 30, 30, 0 };
		panel.setLayout(gbl_panel);

		scRed = new JScrollBar();

		scRed.setBackground(Color.RED);
		scRed.setMaximum(255);
		scRed.setOrientation(JScrollBar.HORIZONTAL);
		GridBagConstraints gbc_scRed = new GridBagConstraints();
		gbc_scRed.fill = GridBagConstraints.HORIZONTAL;
		gbc_scRed.insets = new Insets(0, 0, 5, 5);
		gbc_scRed.gridwidth = 2;
		gbc_scRed.gridy = 0;
		gbc_scRed.gridx = 0;
		panel.add(scRed, gbc_scRed);

		txtRed = new JTextField();

		txtRed.setText("0");
		GridBagConstraints gbc_txtRed = new GridBagConstraints();
		gbc_txtRed.insets = new Insets(0, 0, 5, 5);
		gbc_txtRed.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtRed.gridx = 2;
		gbc_txtRed.gridy = 0;
		panel.add(txtRed, gbc_txtRed);
		txtRed.setColumns(10);

		scGreen = new JScrollBar();

		scGreen.setOrientation(JScrollBar.HORIZONTAL);
		scGreen.setMaximum(255);
		scGreen.setBackground(Color.GREEN);
		GridBagConstraints gbc_scGreen = new GridBagConstraints();
		gbc_scGreen.fill = GridBagConstraints.HORIZONTAL;
		gbc_scGreen.insets = new Insets(0, 0, 5, 5);
		gbc_scGreen.gridwidth = 2;
		gbc_scGreen.gridy = 1;
		gbc_scGreen.gridx = 0;
		panel.add(scGreen, gbc_scGreen);

		txtGreen = new JTextField();
		txtGreen.setText("0");
		txtGreen.setColumns(10);
		GridBagConstraints gbc_txtGreen = new GridBagConstraints();
		gbc_txtGreen.insets = new Insets(0, 0, 5, 5);
		gbc_txtGreen.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtGreen.gridx = 2;
		gbc_txtGreen.gridy = 1;
		panel.add(txtGreen, gbc_txtGreen);

		lblGreen = new JLabel("New label");
		GridBagConstraints gbc_lblGreen = new GridBagConstraints();
		gbc_lblGreen.insets = new Insets(0, 0, 5, 0);
		gbc_lblGreen.gridx = 3;
		gbc_lblGreen.gridy = 1;
		panel.add(lblGreen, gbc_lblGreen);

		scBlue = new JScrollBar();
		scBlue.setOrientation(JScrollBar.HORIZONTAL);
		scBlue.setMaximum(255);
		scBlue.setBackground(Color.BLUE);
		GridBagConstraints gbc_scBlue = new GridBagConstraints();
		gbc_scBlue.fill = GridBagConstraints.HORIZONTAL;
		gbc_scBlue.insets = new Insets(0, 0, 5, 5);
		gbc_scBlue.gridwidth = 2;
		gbc_scBlue.gridy = 2;
		gbc_scBlue.gridx = 0;
		panel.add(scBlue, gbc_scBlue);

		txtBlue = new JTextField();
		txtBlue.setText("0");
		txtBlue.setColumns(10);
		GridBagConstraints gbc_txtBlue = new GridBagConstraints();
		gbc_txtBlue.insets = new Insets(0, 0, 5, 5);
		gbc_txtBlue.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtBlue.gridx = 2;
		gbc_txtBlue.gridy = 2;
		panel.add(txtBlue, gbc_txtBlue);

		lblRed = new JLabel("New label");
		GridBagConstraints gbc_lblRed = new GridBagConstraints();
		gbc_lblRed.insets = new Insets(0, 0, 5, 0);
		gbc_lblRed.gridx = 3;
		gbc_lblRed.gridy = 0;
		panel.add(lblRed, gbc_lblRed);

		lblBlue = new JLabel("New label");
		GridBagConstraints gbc_lblBlue = new GridBagConstraints();
		gbc_lblBlue.insets = new Insets(0, 0, 5, 0);
		gbc_lblBlue.gridx = 3;
		gbc_lblBlue.gridy = 2;
		panel.add(lblBlue, gbc_lblBlue);

		pnPreview = new JPanel();
		GridBagConstraints gbc_pnPreview = new GridBagConstraints();
		gbc_pnPreview.gridheight = 2;
		gbc_pnPreview.insets = new Insets(0, 0, 0, 5);
		gbc_pnPreview.fill = GridBagConstraints.BOTH;
		gbc_pnPreview.gridx = 0;
		gbc_pnPreview.gridy = 3;
		panel.add(pnPreview, gbc_pnPreview);

		pnRadioButtons = new JPanel();
		GridBagConstraints gbc_pnRadioButtons = new GridBagConstraints();
		gbc_pnRadioButtons.gridheight = 2;
		gbc_pnRadioButtons.insets = new Insets(0, 0, 5, 5);
		gbc_pnRadioButtons.fill = GridBagConstraints.BOTH;
		gbc_pnRadioButtons.gridx = 1;
		gbc_pnRadioButtons.gridy = 3;
		panel.add(pnRadioButtons, gbc_pnRadioButtons);
		GridBagLayout gbl_pnRadioButtons = new GridBagLayout();
		gbl_pnRadioButtons.columnWidths = new int[] { 0 };
		gbl_pnRadioButtons.rowHeights = new int[] { 0 };
		gbl_pnRadioButtons.columnWeights = new double[] { Double.MIN_VALUE };
		gbl_pnRadioButtons.rowWeights = new double[] { Double.MIN_VALUE };
		pnRadioButtons.setLayout(gbl_pnRadioButtons);

		btDarker = new JButton("Darker");

		GridBagConstraints gbc_btDarker = new GridBagConstraints();
		gbc_btDarker.insets = new Insets(0, 0, 5, 5);
		gbc_btDarker.gridx = 2;
		gbc_btDarker.gridy = 3;
		panel.add(btDarker, gbc_btDarker);

		btLighter = new JButton("Lighter");

		GridBagConstraints gbc_btLighter = new GridBagConstraints();
		gbc_btLighter.insets = new Insets(0, 0, 5, 5);
		gbc_btLighter.gridx = 2;
		gbc_btLighter.gridy = 4;
		panel.add(btLighter, gbc_btLighter);

	}

	@Override
	public void update(Observable obs, Object obj) {
		if (obs == currColor) {
			ignoreChanges = true;
			scRed.setValue(currColor.getRed());
			txtRed.setText(currColor.getRed() + "");
			lblRed.setText(Integer.toHexString(currColor.getRed()));

			scGreen.setValue(currColor.getGreen());
			txtGreen.setText(currColor.getGreen() + "");
			lblGreen.setText(Integer.toHexString(currColor.getGreen()));

			scBlue.setValue(currColor.getBlue());
			txtBlue.setText(currColor.getBlue() + "");
			lblBlue.setText(Integer.toHexString(currColor.getBlue()));

			pnPreview.setBackground(new Color(currColor.getRed(), currColor.getGreen(), currColor.getBlue()));
			ignoreChanges = false;
		}
	}

}
