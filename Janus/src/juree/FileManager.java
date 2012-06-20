package juree;
import java.awt.Color;
import java.awt.Component;
import java.awt.FileDialog;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;



public class FileManager{
	MainFrame_sub mf;
	static String filename=null;
	
	FileManager(MainFrame_sub mf){
		this.mf = mf;
	}
	
	public static void newFileMethod(MainFrame_sub mf){
		/*new���� ����!*/
		
		/*�ؽ�Ʈ�ʵ� �ʱ�ȭ*/
		mf.attrPane.xText.setText("");
		mf.attrPane.yText.setText("");
		mf.attrPane.widthText.setText("");
		mf.attrPane.heightText.setText("");
		mf.attrPane.nodeTextT.setText("");
		mf.attrPane.colorCombo.setSelectedIndex(0);
		
		/*MindMapPane �ʱ�ȭ*/
		Component[] com = mf.mapPane.getComponents();
		
		for(int i=0; i<com.length; i++){
			com[i].setVisible(false);
		}
		
		/*���� �ʱ�ȭ*/
		MainFrame_sub.nd.clear();
		
		/*����ȣ �ʱ�ȭ*/
		MainFrame_sub.myNum=0;
	}
	
	public static void openFileMethod(MainFrame_sub mf){
		/*���Ͽ���*/
		
		//�����Ϻ��� ȣ��
		FileManager.newFileMethod(mf);
			
		FileDialog fileOpen = new FileDialog(MainFrame_sub.me, "Open", FileDialog.LOAD);
		fileOpen.setVisible(true);
	
		if(fileOpen.getFile()==null)
			return;
		
		filename=fileOpen.getFile();
		FileManager.openXML(filename);
		MainFrame_sub.myNum=MainFrame_sub.nd.size();
		
	}
	
	public static void saveFileMethod(MainFrame_sub mf){
		/*��������*/
		System.out.println(filename);
		if(filename==null){
			FileDialog fileSave = new FileDialog(MainFrame_sub.me, "Save", FileDialog.SAVE);
			fileSave.setVisible(true);
			
			if(fileSave.getFile()==null)
				return;
			
			filename=fileSave.getFile();
			
			FileManager.saveToXML(filename);
			//������� �� ����ó��.
		}else{
			FileManager.saveToXML(filename);
		}
		
	}
	
	public static void saveAsFileMethod(MainFrame_sub mf){
		/*���� �ٸ��̸����� ����*/
		
		FileDialog fileSaveAs = new FileDialog(MainFrame_sub.me, "SaveAs", FileDialog.SAVE);
		fileSaveAs.setFile(filename);
		fileSaveAs.setVisible(true);
		
		if(fileSaveAs.getFile()==null)
			return;
				
		FileManager.saveToXML(fileSaveAs.getFile());
	}
	
	public static void sendXML(){
		try{
			DataInputStream dis = null;

			String ip= JOptionPane.showInputDialog("������ IP �ּ�(ex>111.111.111.000) :");

			FileDialog fileOpen = new FileDialog(MainFrame_sub.me, "Open", FileDialog.LOAD);
			fileOpen.setVisible(true);

			if(fileOpen.getFile()==null)
				return;

			String fileName =fileOpen.getFile();

			File file = new File(fileName);

			DatagramSocket ds = new DatagramSocket();
			InetAddress ia = InetAddress.getByName(ip);

			//start?
			String str = "start";
			DatagramPacket dp = new DatagramPacket(str.getBytes(), str.getBytes().length, ia, 7777);
			ds.send(dp);

			dis = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
			byte[] by = new byte[65508];

			while(true){
				int xx = dis.read(by, 0, by.length);
				if(xx == -1) break;
				dp=new DatagramPacket(by, xx, ia, 7777);
				ds.send(dp);
			}

			str= "end";
			dp = new DatagramPacket(str.getBytes(), str.getBytes().length, ia, 7777);

			ds.send(dp);

			ds.close();

			JOptionPane.showMessageDialog(MainFrame_sub.me, "���ۿϷ�");

			
		}catch(IOException e){

		}
	}
	
	public static void receiveXML(){
		try{
			DatagramSocket soc = new DatagramSocket(7777);
			JOptionPane.showMessageDialog(MainFrame_sub.me, "���۹��� �غ� �Ϸ�");
			File file = null;
			DataOutputStream dos = null;

			while(true){
				DatagramPacket dp = new DatagramPacket(new byte[65508], 65508);
				soc.receive(dp);
				String str = new String(dp.getData()).trim();
				if(str.equalsIgnoreCase("start")){
					//���۵ǰ�����
					FileDialog fileSaveAs = new FileDialog(MainFrame_sub.me, "SaveAs", FileDialog.SAVE);
					fileSaveAs.setVisible(true);

					if(fileSaveAs.getFile()==null)
						return;

					String fileName =fileSaveAs.getFile();

					file = new File(fileName);
					dos=new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
				}
				else if(str.equalsIgnoreCase("end")){
					JOptionPane.showMessageDialog(MainFrame_sub.me, "���ۿϷ�");
					dos.close();
					break;
				}
				else if(file!=null){
					dos.write(str.getBytes(), 0, str.getBytes().length);
				}

			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	

	public static void saveToXML(String fileName)
	{
		Vector<Node> vt = MainFrame_sub.nd;
		try{        
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();        
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			// ��Ʈ ������Ʈ        
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("NodeList");
			doc.appendChild(rootElement);
			
			// Node ������Ʈ        
			for(int i=0;i<vt.size();i++)
			{
				Node temp = vt.get(i);
				Element node = doc.createElement("node");
				rootElement.appendChild(node);

				// ���ѹ� ������Ʈ
				Element id = doc.createElement("id");
				id.appendChild(doc.createTextNode(temp.num+""));
				node.appendChild(id);

				// xpos ������Ʈ        
				Element xPos = doc.createElement("xPos");
				xPos.appendChild(doc.createTextNode(temp.x+""));
				node.appendChild(xPos);
				// ypos ������Ʈ        
				Element yPos = doc.createElement("yPos"); 
				yPos.appendChild(doc.createTextNode(temp.y+"")); 
				node.appendChild(yPos);        
				// width ������Ʈ        
				Element width = doc.createElement("width");        
				width.appendChild(doc.createTextNode(temp.width+""));   
				node.appendChild(width);        
				// height ������Ʈ       
				Element height = doc.createElement("height");     
				height.appendChild(doc.createTextNode(temp.height+""));      
				node.appendChild(height);  
				// text ������Ʈ       
				Element text = doc.createElement("text");     
				text.appendChild(doc.createTextNode(temp.nodeText));      
				node.appendChild(text);  
				Element link = doc.createElement("link");
				
				link.appendChild(doc.createTextNode(temp.parent+""));
				node.appendChild(link);
			}

			TransformerFactory transformerFactory = TransformerFactory.newInstance();     
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(doc);
			StreamResult result = null;
			try {
				result = new StreamResult(new FileOutputStream(new File(fileName)));
			}catch(IOException ee) {
				
			}

			transformer.transform(source, result);    
			System.out.println("File saved!");
		}
		catch (ParserConfigurationException pce) {      
			pce.printStackTrace();
		}
		catch (TransformerException tfe) {        
			tfe.printStackTrace();
		}
		
		
	}
	
	public static void openXML(String filename){
		  try{
			   DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			   DocumentBuilder parser = dbf.newDocumentBuilder();
			   Document doc = parser.parse(filename);
			   Element root = doc.getDocumentElement();
			   
			   NodeList node_list = root.getElementsByTagName("node");
			   int len = node_list.getLength();
			   for(int i=0;i<len;i++)
			   {
			    node_list = root.getElementsByTagName("id");
			    String id = node_list.item(i).getFirstChild().getNodeValue();
			    node_list = root.getElementsByTagName("xPos");
			    int x = Integer.parseInt(node_list.item(i).getFirstChild().getNodeValue());
			    node_list = root.getElementsByTagName("yPos");
			    int y = Integer.parseInt(node_list.item(i).getFirstChild().getNodeValue());
			    node_list = root.getElementsByTagName("width");
			    int w = Integer.parseInt(node_list.item(i).getFirstChild().getNodeValue());
			    node_list = root.getElementsByTagName("height");
			    int h = Integer.parseInt(node_list.item(i).getFirstChild().getNodeValue());
			    node_list = root.getElementsByTagName("text");
			    String t = node_list.item(i).getFirstChild().getNodeValue();
			    node_list = root.getElementsByTagName("link");
			    String link = null;
			    link = node_list.item(i).getFirstChild().getNodeValue();
			    
			    Node newNode = new Node(x,y);
			    newNode.num=Integer.parseInt(id);
			    newNode.width=w;
			    newNode.height=h;
			    newNode.nodeText=t;
			    newNode.parent=Integer.parseInt(link);
			    
			    newNode.update();
						    
			    MainFrame_sub.me.mapPane.setBackground(Color.BLACK);
			    MainFrame_sub.me.mapPane.setBackground(Color.WHITE);
			    
			    MainFrame_sub.nd.add(newNode);
			    MainFrame_sub.me.mapPane.add(newNode);
			   }
			  }catch(Exception ee) {
			   ee.printStackTrace();
			  }
	}

}
