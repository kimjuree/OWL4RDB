package juree;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.util.Vector;

class MainFrame_sub extends JFrame{
	static MainFrame_sub me;
	static Vector<Node> nd;
	static int myNum;
	static{
		nd = new Vector<Node>();
	}
	/*메뉴바 생성*/
	JMenuBar menubar = new JMenuBar();
	JMenu file = new JMenu("File");
	JMenuItem fileNew = new JMenuItem("New");
	JMenuItem fileOpen = new JMenuItem("Open");
	JMenuItem fileSave = new JMenuItem("Save");
	JMenuItem fileSaveAs = new JMenuItem("Save as");
	JMenuItem fileClose = new JMenuItem("Close");
	
	JMenu etc = new JMenu("Tool");
	JMenuItem netSend = new JMenuItem("Send XML");
	JMenuItem netRecv = new JMenuItem("Receive XML");
	
	/*툴바 생성*/
	JToolBar bar = new JToolBar();
	JButton NewFile = new JButton();
	JButton OpenFile = new JButton();
	JButton SaveFile = new JButton();
	JButton SaveAsFile = new JButton();
	JButton CloseFile = new JButton();
	JButton SendNet = new JButton();
	JButton RecvNet = new JButton();
	
	ImageIcon NewImg = new ImageIcon("images/NewImg.PNG");
	ImageIcon OpenImg = new ImageIcon("images/OpenImg.PNG");
	ImageIcon SaveImg = new ImageIcon("images/SaveImg.PNG");
	ImageIcon SaveAsImg = new ImageIcon("images/SaveAsImg.PNG");
	ImageIcon CloseImg = new ImageIcon("images/CloseImg.PNG");
	ImageIcon SendImg = new ImageIcon("images/SendImg.PNG");
	ImageIcon RecvImg = new ImageIcon("images/RecvImg.PNG");
	
	Graphics g;
	
	
	AttributePane attrPane;
	MindMapPane mapPane;
	
	MainFrame_sub(){
		me = this;
	
		/*메뉴 단축키*/
		fileNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,KeyEvent.CTRL_MASK));
		fileOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,KeyEvent.CTRL_MASK));
		fileSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,KeyEvent.CTRL_MASK));
		fileSaveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,KeyEvent.CTRL_MASK));
		fileClose.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,KeyEvent.CTRL_MASK));
		netSend.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,KeyEvent.CTRL_MASK));
		netRecv.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,KeyEvent.CTRL_MASK));
		
		
		/*메뉴바*/
		fileNew.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				FileManager.newFileMethod(me);
			}
		});
		fileOpen.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				FileManager.openFileMethod(me);
			}
		});
		fileSave.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				FileManager.saveFileMethod(me);
			}
		});
		fileSaveAs.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				FileManager.saveAsFileMethod(me);
			}
		});
		fileClose.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		
		netSend.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				FileManager.sendXML();
			}
		});
		
		netRecv.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				FileManager.receiveXML();
			}
		});
		
		
		
		file.add(fileNew);
		file.add(fileOpen);
		file.add(fileSave);
		file.add(fileSaveAs);
		file.add(fileClose);
		
		menubar.add(file);
		
		etc.add(netSend);
		etc.add(netRecv);
		
		menubar.add(etc);
		
		
		/*툴바*/

		NewFile.setIcon(NewImg);
		OpenFile.setIcon(OpenImg);
		SaveFile.setIcon(SaveImg);
		SaveAsFile.setIcon(SaveAsImg);
		CloseFile.setIcon(CloseImg);
		SendNet.setIcon(SendImg);
		RecvNet.setIcon(RecvImg);
		
		NewFile.setToolTipText("새로만들기");
		OpenFile.setToolTipText("파일열기");
		SaveFile.setToolTipText("저장");
		SaveAsFile.setToolTipText("다른이름으로저장");
		CloseFile.setToolTipText("프로그램종료");
		SendNet.setToolTipText("파일전송");
		RecvNet.setToolTipText("파일받기");
		
		
		NewFile.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				FileManager.newFileMethod(me);
			}
		});
		OpenFile.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				FileManager.openFileMethod(me);
			}
		});
		SaveFile.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				FileManager.saveFileMethod(me);
			}
		});
		SaveAsFile.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				FileManager.saveAsFileMethod(me);
			}
		});
		CloseFile.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		
		SendNet.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				FileManager.sendXML();
			}
		});
		
		RecvNet.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				FileManager.receiveXML();
			}
		});
		
		
		bar.add(NewFile);
		bar.addSeparator();
		bar.add(OpenFile);
		bar.addSeparator();
		bar.add(SaveFile);
		bar.addSeparator();
		bar.add(SaveAsFile);
		bar.addSeparator();
		bar.add(CloseFile);
		bar.addSeparator();
		bar.addSeparator();
		bar.add(SendNet);
		bar.addSeparator();
		bar.add(RecvNet);
		bar.addSeparator();
		
		/*panel*/
		attrPane = new AttributePane();
		mapPane = new MindMapPane();
		
		JPanel sep = new JPanel();
		sep.setLayout(new BorderLayout());
		sep.add(attrPane, BorderLayout.WEST);
		sep.add(mapPane, BorderLayout.CENTER);
		
		/*프레임 설정*/
		this.setJMenuBar(menubar);
		this.setTitle("MindMap");
		this.setBounds(120, 120, 800, 500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		this.add(bar, BorderLayout.NORTH);
		this.add(sep, BorderLayout.CENTER);
		
		attrPane.setBorder(new LineBorder(Color.black));
		mapPane.setLayout(null);
		
		this.setVisible(true);
		mapPane.addMouseListener(new MyMouse());
		
		/*화면에 선 그리기*/
		g =MainFrame_sub.me.mapPane.getGraphics();
		DrawThread dt = new DrawThread();
		dt.start();
		
	}

	
	class MyMouse implements MouseListener{
		
		public void mouseClicked(MouseEvent e) {
		
			Vector<Node> vt = MainFrame_sub.nd;
			boolean isSeleted=false;
			int x= e.getX();
			int y= e.getY();
					
			attrPane.xText.setText(String.valueOf(x));
			attrPane.yText.setText(String.valueOf(y));
			
			/*노드생성*/
			Node node = new Node(x, y);
			
			/*부모노드 지정*/
			for(int i=0; i<vt.size(); i++){
				if(vt.get(i).selected){
					node.parent= vt.get(i).num;
					node.num=MainFrame_sub.myNum++;
					isSeleted = true;
				}
			}
			
			if(vt.isEmpty()){
				node.parent=-1;
				node.num=MainFrame_sub.myNum++;
			}
			
			if(!isSeleted&&!vt.isEmpty()){
				//노드생성하지말고 넘어가야 함.
				JOptionPane.showMessageDialog(MainFrame_sub.me, "노드 한개 선택 후 자식생성");
			}else{
				/*화면에 노드 추가*/
				mapPane.add(node);
			
				/*벡터에 노드 추가*/
				nd.add(node);
				
				
				/*텍스트필드에 삽입*/
				attrPane.widthText.setText(String.valueOf(node.width));
				attrPane.heightText.setText(node.height+"");
				attrPane.nodeTextT.setText(node.nodeText);
								
				/*화면에 선 추가*/
			
				MainFrame_sub.me.drawLine();
			
				
			
				mapPane.setBackground(Color.black);
				mapPane.setBackground(Color.white);
			}
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}

		public void mousePressed(MouseEvent e) {
		}

		public void mouseReleased(MouseEvent e) {
		}

	}
	
	public void drawLine(){
		//mapPane.repaint();
		Vector<Node> vt=MainFrame_sub.nd;
		int temp[][] = new int[2][2];
		
		for(int i=0; i<vt.size(); i++){
			vt.get(i).update();
			vt.get(i).locationSet();
		}
		
		//System.out.println("drawLine()");
		
		for(int i=0; i<vt.size(); i++){
			if(vt.get(i).parent==-1){
				continue;
			}else{
				for(int j=0; j<vt.size(); j++){
					//System.out.println("parent : " + vt.get(i).parent + " " + vt.get(j).parent);
					if(vt.get(j).num == vt.get(i).parent){
						calc(vt.get(i).location, vt.get(j).location, temp);
						g.drawLine(temp[0][0], temp[0][1], temp[1][0], temp[1][1]);
						
	
						//System.out.println(vt.get(j).num +" " + vt.get(i).parent);
						//System.out.println("들어와?");
					}
					
				}
			}
			
		}
	}

	private class DrawThread extends Thread{
		public void run(){
			while(true){
				drawLine();
				try{
					Thread.sleep(100);
				}catch(Exception e){
					
				}
				MainFrame_sub.me.mapPane.update(g);
			}
		}
	}
	
	public int[][] calc(int loca1[][], int loca2[][], int temp[][]){
		int minX=10000;
		int minY=10000;
		int xPos;
		int yPos;
		int minI=0;
		int minJ=0;
		
		for(int i=0; i<4; i++){
			
			for(int j=0; j<4; j++){
				xPos=loca1[i][0]-loca2[j][0];  
				yPos=loca1[i][1]-loca2[j][1]; 
				
				xPos= Math.abs(xPos);
				yPos= Math.abs(yPos);
				
				if(xPos<=minX && yPos<=minY){
					minX=xPos;
					minY=yPos;
					
					minI=i;
					minJ=j;
				}	
			}
		}
		
		temp[0][0]= loca1[minI][0];
		temp[0][1]= loca1[minI][1];
		temp[1][0]= loca2[minJ][0];
		temp[1][1]= loca2[minJ][1];
		
		return temp;
		
	}
	
}


public class MainFrame {
	public static void main(String arg[]){
		MainFrame_sub mfs = new MainFrame_sub();
	}
}
