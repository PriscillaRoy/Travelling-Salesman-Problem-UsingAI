/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

/**
 *
 * @author priscilla
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Enumeration;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicOptionPaneUI;

public class GUIBuilder extends JFrame {

	String file_path;
	String search_fn = null;
	String cost_fn = null;

	int nodes_number = 0, rand_number = 0;
	int number_of_testCases = 0;

	public GUIBuilder() {
		createView();

	}

	String getSelectedButton(ButtonGroup buttonslist) {
		for (Enumeration<AbstractButton> buttons = buttonslist.getElements(); buttons.hasMoreElements();) {
			AbstractButton button = buttons.nextElement();
			if (button.isSelected()) {
				return button.getText();
			}
		}
		return null;
	}

	private void createView() {
		JFrame frame = new JFrame();

		JPanel panel = new JPanel(new BorderLayout());

		JPanel panelNorth = new JPanel();
		panel.add(panelNorth, BorderLayout.NORTH);
		JPanel panelSouth = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel.add(panelSouth, BorderLayout.SOUTH);

		JPanel panelWest = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.NONE;
		panel.add(panelWest, BorderLayout.WEST);

		JPanel panelEast = new JPanel(new GridBagLayout());
		GridBagConstraints gbc_1 = new GridBagConstraints();
		gbc_1.fill = GridBagConstraints.NONE;
		panel.add(panelEast, BorderLayout.EAST);

		frame.getContentPane().add(panel);
		JLabel message_top = new JLabel("Please Select The Parameters: TSP", JLabel.CENTER);
		message_top.setPreferredSize(new Dimension(250, 50));

		frame.getContentPane().add(panel);
		JLabel message_east = new JLabel("Please Select the file to get data", JLabel.CENTER);
		message_east.setPreferredSize(new Dimension(250, 50));

		gbc_1.insets = new Insets(0, 0, 0, 0);
		gbc_1.weightx = 0.2;
		gbc_1.weighty = 0.2;
		gbc_1.gridx = 0;
		gbc_1.gridy = 0;
		panelEast.add(message_east, gbc_1);

		JLabel file_chosen = new JLabel("", JLabel.CENTER);
		message_east.setPreferredSize(new Dimension(250, 50));

		JButton open = new JButton("Browse / Choose ");
		JTextArea text_file_path = new JTextArea();

		text_file_path.setPreferredSize(new Dimension(200, 30));

		gbc_1.insets = new Insets(0, 0, 0, 0);
		gbc_1.weightx = 0.2;
		gbc_1.weighty = 0.2;
		gbc_1.gridx = 0;
		gbc_1.gridy = 2;
		panelEast.add(open, gbc_1);
		gbc_1.insets = new Insets(0, 0, 0, 0);
		gbc_1.weightx = 0.2;
		gbc_1.weighty = 0.2;
		gbc_1.gridx = 0;
		gbc_1.gridy = 1;
		panelEast.add(text_file_path, gbc_1);
		gbc_1.insets = new Insets(0, 0, 0, 0);
		gbc_1.weightx = 0.2;
		gbc_1.weighty = 0.2;
		gbc_1.gridx = 0;
		gbc_1.gridy = 3;

		panelEast.add(file_chosen, gbc_1);

		open.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				JFileChooser fc = new JFileChooser();
				fc.setCurrentDirectory(new java.io.File("."));
				fc.setDialogTitle("Choose the File");
				fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				int result = fc.showOpenDialog(panelEast);

				if (result == JFileChooser.APPROVE_OPTION) {

					text_file_path.setText(fc.getSelectedFile().getAbsolutePath());
					file_chosen.setText("File Successfully Chosen!");

				}

				file_path = fc.getSelectedFile().getAbsolutePath();

			}
		});

		JButton file_submit = new JButton("Run TSP");
		gbc_1.insets = new Insets(0, 0, 0, 0);
		gbc_1.weightx = 0.2;
		gbc_1.weighty = 0.2;
		gbc_1.gridx = 0;
		gbc_1.gridy = 4;

		panelEast.add(file_submit, gbc_1);

		file_submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String line = null;

				try {

					FileReader fileReader;

					fileReader = new FileReader(file_path);

					BufferedReader bufferedReader = new BufferedReader(fileReader);

					BufferedWriter bufferedWriter = null;
					FileWriter fw = null;
					fw = new FileWriter("/Users/priscilla/Documents/Artificial Intelligence/Project_1_TSP/2runs.txt");
					bufferedWriter = new BufferedWriter(fw);

					while ((line = bufferedReader.readLine()) != null) {

						String[] parts = line.split(",");
						search_fn = parts[0];
						cost_fn = parts[1];
						nodes_number = Integer.parseInt(parts[2]);
						rand_number = Integer.parseInt(parts[3]);

						if (search_fn.equalsIgnoreCase("SIM")) {

							TestTSPNN tsp = new TestTSPNN();

							tsp.setsearch_Strategy(search_fn);

							tsp.setcost_Function(cost_fn);

							tsp.setnumberOfNodes(nodes_number);
							tsp.begin();
							int temp_cost = 0;
							temp_cost = tsp.getcost();

							bufferedWriter.write(search_fn);
							bufferedWriter.flush();
							bufferedWriter.newLine();
							bufferedWriter.flush();

							bufferedWriter.write(cost_fn);
							bufferedWriter.flush();
							bufferedWriter.newLine();
							bufferedWriter.flush();

							bufferedWriter.write(String.valueOf(temp_cost));
							bufferedWriter.flush();
							bufferedWriter.newLine();
							bufferedWriter.flush();

						} else if (search_fn.equalsIgnoreCase("SOPH")) {
							TestTSPGA tsp = new TestTSPGA();
							tsp.setsearch_Strategy(search_fn);

							tsp.setcost_Function(cost_fn);

							tsp.setnumberOfNodes(nodes_number);

							tsp.setmeb_variable(rand_number);
							tsp.begin();
							int temp_cost = tsp.getcost();

							bufferedWriter.write(search_fn);
							bufferedWriter.flush();
							bufferedWriter.newLine();
							bufferedWriter.flush();

							bufferedWriter.write(cost_fn);
							bufferedWriter.flush();
							bufferedWriter.newLine();
							bufferedWriter.flush();

							bufferedWriter.write(String.valueOf(temp_cost));
							bufferedWriter.flush();
							bufferedWriter.newLine();
							bufferedWriter.flush();

						}

					}
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.weightx = 1.0;
		gbc.weighty = 0.15;
		gbc.gridx = 0;
		gbc.gridy = 0;

		panelWest.add(message_top, gbc);

		JLabel search_label = new JLabel();
		search_label.setPreferredSize(new Dimension(160, 15));
		search_label.setText("Search Strategy");
		// gbc.insets = new Insets(5, 25, 5, 5);
		 gbc.weightx = 0.2;
		 gbc.weighty = 0.15;
		gbc.gridx = 0;
		gbc.gridy = 1;
		panelWest.add(search_label, gbc);

		JRadioButton search_SIM = new JRadioButton("SIM");
		JRadioButton search_SOPH = new JRadioButton("SOPH");
		//search_SIM.setBounds(75, 50, 250, 30);
		//search_SOPH.setBounds(75, 100, 250, 30);

		ButtonGroup SearchGroup = new ButtonGroup();
		SearchGroup.add(search_SIM);
		SearchGroup.add(search_SOPH);
		gbc.weightx = 0.4;
		gbc.weighty = 0.15;
		gbc.gridx = 1;
		gbc.gridy = 1;
		panelWest.add(search_SIM, gbc);
		gbc.weightx = 0.4;
		gbc.weighty = 0.15;
		gbc.gridx = 2;
		gbc.gridy = 1;
		panelWest.add(search_SOPH, gbc);

		JLabel cost_label = new JLabel();
		cost_label.setPreferredSize(new Dimension(150, 15));
		cost_label.setText("Cost Function");
		gbc.weightx = 0.2;

		gbc.weighty = 0.15;
		gbc.gridx = 0;
		gbc.gridy = 2;
		panelWest.add(cost_label, gbc);

		JRadioButton cost_f1 = new JRadioButton("C1");
		JRadioButton cost_f2 = new JRadioButton("C2");
		JRadioButton cost_f3 = new JRadioButton("C3");
		cost_f1.setBounds(25, 50, 80, 30);
		cost_f2.setBounds(25, 80, 80, 30);
		cost_f2.setBounds(25, 110, 100, 30);

		ButtonGroup CostGroup = new ButtonGroup();
		CostGroup.add(cost_f1);
		CostGroup.add(cost_f2);
		CostGroup.add(cost_f3);
		gbc.weightx = 0.26;
		gbc.weighty = 0.15;
		gbc.gridx = 1;
		gbc.gridy = 2;
		panelWest.add(cost_f1, gbc);
		gbc.weightx = 0.2;
		gbc.weighty = 0.15;
		gbc.gridx = 2;
		gbc.gridy = 2;
		panelWest.add(cost_f2, gbc);
		gbc.weightx = 0.34;
		gbc.weighty = 0.15;
		gbc.gridx = 3;
		gbc.gridy = 2;
		panelWest.add(cost_f3, gbc);

		// Number of Cities N
		JLabel number_of_cities_label = new JLabel();
		number_of_cities_label.setPreferredSize(new Dimension(150, 15));
		number_of_cities_label.setText("Number Of Cities : N");
		gbc.weightx = 0.3;
		gbc.weighty = 0.15;
		gbc.gridx = 0;
		gbc.gridy = 3;
		panelWest.add(number_of_cities_label, gbc);

		JTextField num_cities = new JTextField();
		num_cities.setPreferredSize(new Dimension(150, 30));
		gbc.weightx = 0.3;
		gbc.weighty = 0.15;
		gbc.gridx = 1;
		gbc.gridy = 3;
		panelWest.add(num_cities, gbc);

		// Maximal Effort Bound
		JLabel random_number_label = new JLabel();
		random_number_label.setPreferredSize(new Dimension(150, 15));
		random_number_label.setText("Random Number Value");
		gbc.weightx = 0.3;
		gbc.weighty = 0.15;
		gbc.gridx = 0;
		gbc.gridy = 4;
		panelWest.add(random_number_label, gbc);

		JTextField random_number_value = new JTextField();
		random_number_value.setPreferredSize(new Dimension(150, 30));
		gbc.weightx = 0.3;
		gbc.weighty = 0.15;
		gbc.gridx = 1;
		gbc.gridy = 4;
		panelWest.add(random_number_value, gbc);

		// Integer Random Number
		JLabel maximal_effort = new JLabel();
		maximal_effort.setPreferredSize(new Dimension(150, 15));
		maximal_effort.setText("Maximal Effort Bound");
		gbc.weightx = 0.3;
		gbc.weighty = 0.15;
		gbc.gridx = 0;
		gbc.gridy = 5;
		panelWest.add(maximal_effort, gbc);

		JTextField maximal_effort_value = new JTextField();
		maximal_effort_value.setPreferredSize(new Dimension(150, 30));
		gbc.weightx = 0.3;
		gbc.weighty = 0.15;
		gbc.gridx = 1;
		gbc.gridy = 5;
		panelWest.add(maximal_effort_value, gbc);

		JButton submit_button = new JButton("Get Optimal Cost");
		gbc.weighty = 0.15;
		gbc.gridx = 1;
		gbc.gridy = 6;
		panelWest.add(submit_button,gbc);

		submit_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// throw new UnsupportedOperationException("Not supported
				// yet."); //To change body of generated methods, choose Tools |
				// Templates.

				String temp = getSelectedButton(SearchGroup);
				if (temp == "SIM") {
					TestTSPNN tsp = new TestTSPNN();
					tsp.setsearch_Strategy(temp);
					temp = getSelectedButton(CostGroup);
					tsp.setcost_Function(temp);
					temp = num_cities.getText();

					tsp.setnumberOfNodes(Integer.parseInt(temp));
					tsp.begin();
					int temp_cost = 0;
					temp_cost = tsp.getcost();

					int[] tour = new int[tsp.getnumberOfNodes()];

					JTextArea textArea = new JTextArea();
					textArea.setText("Cost using Simple Greedy Search is " + temp_cost);
					JScrollPane scrollPane = new JScrollPane(textArea);
					scrollPane.setPreferredSize(new Dimension(500, 500));
					scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
					JOptionPane.showMessageDialog(
							null, scrollPane, "Cost using Simple Greedy Search is " + temp_cost
									+ "\n Route_Solution is : \n" + Arrays.toString(tour),
							JOptionPane.INFORMATION_MESSAGE);

				} else if (temp == "SOPH") {
					TestTSPGA tsp = new TestTSPGA();
					tsp.setsearch_Strategy(temp);
					temp = getSelectedButton(CostGroup);

					tsp.setcost_Function(temp);
					temp = num_cities.getText();

					tsp.setnumberOfNodes(Integer.parseInt(temp));

					tsp.setmeb_variable(Integer.parseInt(maximal_effort_value.getText()));
					tsp.begin();
					int temp_cost = tsp.getcost();

					int[] tour = new int[tsp.getnumberOfNodes()];

					for (int len = 0; len < tsp.getnumberOfNodes(); len++) {
						tour[len] = tsp.gettour()[len];
					}

					JTextArea textArea = new JTextArea();
					textArea.setText("Cost using Sophisticated Genetic Algorithm is " + temp_cost
							+ "\n Route_Solution is : \n" + Arrays.toString(tour));
					JScrollPane scrollPane = new JScrollPane(textArea);
					scrollPane.setPreferredSize(new Dimension(500, 500));
					scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

					JOptionPane.showMessageDialog(null,
							scrollPane, "Cost using Sophisticated Genetic Algorithm is " + temp_cost
									+ "\n Route_Solution is : \n" + Arrays.toString(tour),
							JOptionPane.INFORMATION_MESSAGE);

				}
				/*
				 * else if(temp == "SOPH"){ TestTSPSA tsp = new TestTSPSA();
				 * tsp.setsearch_Strategy(temp); temp =
				 * getSelectedButton(CostGroup); // System.out.println(
				 * "GUI Cost selection::: "+ temp);
				 * 
				 * tsp.setcost_Function(temp); temp = num_cities.getText();
				 * 
				 * tsp.setnumberOfNodes(Integer.parseInt(temp)); // temp =
				 * rand_number_value.getText();
				 * tsp.setRandom_variable(Integer.parseInt(maximal_effort_value.
				 * getText())); tsp.begin(); int temp_cost = tsp.getcost(); //
				 * System.out.println("Cost in JFrame is" + temp_cost); int[]
				 * tour = new int[tsp.getnumberOfNodes()]; // {1,2,3,4};
				 * 
				 * for (int len = 0; len < tsp.getnumberOfNodes(); len++) {
				 * tour[len] = tsp.gettour()[len]; }
				 * 
				 * JTextArea textArea = new JTextArea(); textArea.setText(
				 * "Cost using Sophisticated Simulated Annealing Algorithm is "
				 * + temp_cost ); JScrollPane scrollPane = new
				 * JScrollPane(textArea); scrollPane.setPreferredSize(new
				 * Dimension(500, 500));
				 * scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.
				 * VERTICAL_SCROLLBAR_ALWAYS);
				 * 
				 * JOptionPane .showMessageDialog(null, scrollPane,
				 * "Cost using Sophisticated Genetic Algorithm is " + temp_cost
				 * + "\n Route_Solution is : \n" + Arrays.toString(tour),
				 * JOptionPane.INFORMATION_MESSAGE);
				 * 
				 * }
				 */

			}
		});
		frame.setSize(new Dimension(950, 800));
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Travelling Salesman Problem");
		frame.setResizable(true);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new GUIBuilder().setVisible(true);
			}

		});
	}

	private class CalculateCostActionListener implements ActionListener {

		@Override

		public void actionPerformed(ActionEvent e) {

			TestTSPNN tsp = new TestTSPNN();

			tsp.setsearch_Strategy("abc");

		}
	}
}
