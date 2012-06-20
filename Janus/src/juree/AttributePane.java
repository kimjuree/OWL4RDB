package juree;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;

public class AttributePane extends JPanel{
	JLabel xLabel = new JLabel("x 좌표 : ",JLabel.CENTER);
	JLabel yLabel = new JLabel("y 좌표 : ",JLabel.CENTER);
	JLabel width = new JLabel("너비 : ",JLabel.CENTER);
	JLabel height = new JLabel("높이 : ",JLabel.CENTER);
	JLabel nodeText = new JLabel("텍스트 : ", JLabel.CENTER);
	//
	JLabel colorLabel = new JLabel("색 : ", JLabel.CENTER);
	String[] colorList = {"pink", "green", "blue", "orange", "gray"};
	JComboBox colorCombo = new JComboBox(colorList);
	//
	JButton change = new JButton("변경");
	
	JTextField xText = new JTextField(10);
	JTextField yText = new JTextField(10);
	JTextField widthText = new JTextField(10);
	JTextField heightText = new JTextField(10);
	JTextField nodeTextT = new JTextField(10);
	
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	
	AttributePane(){
		this.setLayout(new BorderLayout());
		
		//
		panel1.setLayout(new GridLayout(6, 2, 0, 30));
		panel1.add(xLabel); panel1.add(xText);
		panel1.add(yLabel); panel1.add(yText);
		panel1.add(width); panel1.add(widthText);
		panel1.add(height); panel1.add(heightText);
		panel1.add(nodeText); panel1.add(nodeTextT);	
		//
		panel1.add(colorLabel); panel1.add(colorCombo);
		
		panel2.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel2.add(change);
		
		this.add(panel2, BorderLayout.SOUTH);
		this.add(panel1, BorderLayout.CENTER);
	
	
		change.addActionListener(new MyActionListener());
	}
	
	class MyActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			Vector<Node> vt = MainFrame_sub.nd;
			
			for(int i=0; i<MainFrame_sub.nd.size(); i++){
				if(vt.get(i).selected){
					vt.get(i).x = Integer.parseInt(xText.getText());
					vt.get(i).y = Integer.parseInt(yText.getText());
					vt.get(i).width = Integer.parseInt(widthText.getText());
					vt.get(i).height = Integer.parseInt(heightText.getText());
					vt.get(i).nodeText = nodeTextT.getText();
					vt.get(i).content.setLocation(vt.get(i).x+vt.get(i).width, vt.get(i).y);
					
					int temp = colorCombo.getSelectedIndex();
					
					switch(temp){
					case 0 :
						vt.get(i).color = Color.pink;
						break;
					case 1 :
						vt.get(i).color = Color.green;
						break;
					case 2 :
						vt.get(i).color = Color.blue;
						break;
					case 3 :
						vt.get(i).color = Color.orange;
						break;
					case 4 :
						vt.get(i).color = Color.gray;
						break;
					}
					
					vt.get(i).setLocation(vt.get(i).x, vt.get(i).y);
					vt.get(i).setText(vt.get(i).nodeText);
					vt.get(i).setSize(vt.get(i).width, vt.get(i).height);
				}
			}
			
		}
	}
	
}
