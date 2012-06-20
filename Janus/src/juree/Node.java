package juree;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;


public class Node extends JLabel{
	int x;
	int y;
	int width;
	int height;
	String nodeText;
	int parent;
	int num;
	int location[][] = new int[4][2];
	boolean selected=false;
	Color color;
	JLabel content = new JLabel();
	
	Node(int x, int y){
		this.x = x;
		this.y = y;
		this.width = 60;
		this.height = 30;
		nodeText = "new node";
		color = Color.pink;
		
		/*선 연결 할 부분 계산*/
		location[0][0]=x+(width/2);
		location[0][1]=y;
		location[1][0]=x+width;
		location[1][1]=y+(height/2);
		location[2][0]=x+(width/2);
		location[2][1]=y+height;
		location[3][0]=x;
		location[3][1]=y+(height/2);
		
		this.setLocation(x, y);
		this.setOpaque(true);
		this.setBackground(color);
		this.setText(nodeText);
		this.setSize(width, height);
		
		
		MyMouseListener listener = new MyMouseListener();
		
		this.addMouseListener(listener);
		this.addMouseMotionListener(listener);
	}
	
	public void locationSet(){
		/*location 설정*/
		location[0][0]=x+(width/2);
		location[0][1]=y;
		location[1][0]=x+width;
		location[1][1]=y+(height/2);
		location[2][0]=x+(width/2);
		location[2][1]=y+height;
		location[3][0]=x;
		location[3][1]=y+(height/2);		
	}
	
	public void update(){
		this.setLocation(x, y);
		this.setOpaque(true);
		if(this.selected==true){
			this.setBackground(Color.red);
		}else{
			this.setBackground(color);
		}
		this.setText(nodeText);
		this.setSize(width, height);
	}
	
	
	
	class MyMouseListener extends MouseMotionAdapter implements MouseListener{
		Point sp;
		
		@Override
		public void mouseClicked(MouseEvent e) {
			/*선택된 노드만 빨강색으로 바뀐다*/
			for(int i =0; i<MainFrame_sub.nd.size(); i++){
				MainFrame_sub.nd.get(i).selected=false;
				MainFrame_sub.nd.get(i).setBackground(Color.pink);
			}
			
			selected=true;
			setBackground(Color.red);
			
			/*선택된 노드의 정보를 텍스트필드에 삽입한다*/
			for(int i=0; i<MainFrame_sub.nd.size(); i++){
				if(MainFrame_sub.nd.get(i).selected){
					MainFrame_sub.me.attrPane.xText.setText(MainFrame_sub.nd.get(i).x+"");
					MainFrame_sub.me.attrPane.yText.setText(MainFrame_sub.nd.get(i).y+"");
					MainFrame_sub.me.attrPane.widthText.setText(MainFrame_sub.nd.get(i).width+"");
					MainFrame_sub.me.attrPane.heightText.setText(MainFrame_sub.nd.get(i).height+"");
					MainFrame_sub.me.attrPane.nodeTextT.setText(MainFrame_sub.nd.get(i).nodeText+"");
					MainFrame_sub.me.attrPane.colorCombo.setSelectedItem(MainFrame_sub.nd.get(i).color);
				}
			}
			
			if(e.getClickCount() == 2)
			{
				String contents = null;
				contents = JOptionPane.showInputDialog("생각을 구체화해서 입력하세요. (취소 = 생각 삭제)");
				if(contents!= null)
				{
					content.setOpaque(true);
					content.setBackground(Color.yellow);
					content.setBounds(x+width, y, width*2, height*2);
					content.setVisible(true);
					content.setText(contents);
					MainFrame_sub.me.mapPane.add(content);
				} else {
					content.setText("");
					content.setVisible(false);
				}
			}
		

			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			sp=e.getPoint();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}
		public void mouseDragged(MouseEvent e){
			Node node = (Node)e.getSource();
				
			Point ep = e.getPoint();
			Point p = node.getLocation();
				
			node.setLocation(p.x + ep.x - sp.x, p.y + ep.y -sp.y);
			
			node.x= node.getX();
			MainFrame_sub.me.attrPane.xText.setText(node.x+"");
			node.y= node.getY();
			MainFrame_sub.me.attrPane.yText.setText(node.y+"");
			node.content.setLocation(node.x+node.width , node.y);
		}
		
	}
}

