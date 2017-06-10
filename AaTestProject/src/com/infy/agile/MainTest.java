package com.infy.agile;

import java.util.ArrayList;
import java.util.Properties;

import com.agile.api.APIException;
import com.agile.api.IAdmin;
import com.agile.api.IAgileSession;
import com.agile.api.IChange;
import com.agile.api.INode;
import com.agile.api.IProperty;
import com.agile.api.IStatus;
import com.agile.api.IUserGroup;
import com.agile.api.NodeConstants;
import com.agile.api.PropertyConstants;

public class MainTest {
	public static IAgileSession session;

	public static void main(String[] args) {
		try {
			session = MyAgileSession.getSession("DEV");
			System.out.println(session + "created");
			MainTest obj = new MainTest();
			obj.testAddApprovers();
			//obj.testNodes();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void testNodes() throws APIException{
		IAdmin adminSession = session.getAdminInstance();
		INode subscriberNode = adminSession.getNode(NodeConstants.NODE_SUBSCRIBERS);
		printChildNodes(subscriberNode);
	}
	
	public void printChildNodes(INode node) throws APIException{
		INode[] childrenNodes = (INode[])node.getChildren();
		System.out.println(childrenNodes.length);
		for (int i = 0; i < childrenNodes.length; i++) {
			System.out.println(childrenNodes[i].getName());
			printChildNodes(childrenNodes[i]);
			//System.out.println(childrenNodes[i].getProperty(PropertyConstants.PROP_DESTINATIONS).getName());
			
			IProperty[] prop = childrenNodes[i].getProperties();
			for (int j = 0; j < prop.length; j++) {
				System.out.println(prop[j].getName());
			}
		}
	}
	
	public void testAddApprovers() throws Exception{
		IChange change = (IChange) session.getObject(IChange.OBJECT_TYPE, "C000031693");
		IUserGroup ug1 = (IUserGroup) session.getObject(IUserGroup.OBJECT_TYPE, "Item Specification Change Analyst - Raw Bulk");
		IUserGroup ug2 = (IUserGroup) session.getObject(IUserGroup.OBJECT_TYPE, "Bulk Stability Review");
		ArrayList list = new ArrayList();
		list.add(ug1);
		list.add(ug2);
		IStatus status = change.getStatus();
		System.out.println(status.getName());
		change.addReviewers(status, list, null, null, false, "");
	}

}
