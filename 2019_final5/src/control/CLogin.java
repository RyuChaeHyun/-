package control;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Vector;

import VO.VOMember;
import dao.DAOMember;
import dao.ELogin;
import entity.EMember;


//Controller
public class CLogin {
	private ELogin eLogin;
	private CBasket cBasket;
	private DAOMember dAOMemeber;
	
	public CLogin() {
		this.eLogin = new ELogin();
		this.dAOMemeber = new DAOMember();
	}

	public int authenticate(Object object) throws FileNotFoundException, UnsupportedEncodingException {
 
		Vector<String> vector = (Vector<String>) object;
		String encodedString = vector.get(0);
		String encodedString2 = vector.get(1);
		
		Decoder decoder = Base64.getDecoder();
		byte[] decodedBytes1 = decoder.decode(encodedString);
		byte[] decodedBytes2 = decoder.decode(encodedString2);
		
		String decodedString = new String(decodedBytes1, "UTF-8");
		String decodedString2 = new String(decodedBytes2, "UTF-8");
		
		System.out.println(decodedString);
		
		System.out.println();

		
		int validUser= eLogin.authenticate(decodedString, decodedString2);
//		boolean validUser2 = cBasket.getCBasket(object);
		return validUser;
	}
	
	public Vector<VOMember> getUsers(Object object) throws FileNotFoundException{
		Vector<Object> vector = (Vector<Object>) object;
		String fileName = (String) vector.get(0);
		Vector<VOMember> vOMembers = new Vector<VOMember>();
		Vector<EMember> eMembers = this.dAOMemeber.getUser(fileName);
		for(EMember eMember : eMembers) {
			VOMember vOMemeber = new VOMember();
			vOMemeber.setId(eMember.getId());
			vOMemeber.setPassword(eMember.getPassword());
			vOMemeber.setName(eMember.getName());
			vOMemeber.setEmail(eMember.getEmail());
			vOMemeber.setMajor(eMember.getMajor());
			
			vOMembers.add(vOMemeber);
		}
		return vOMembers;
	}
	
	
	
}
