package gitrepostats;

import java.io.File;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.wm.utils.DbConn;


import utils.Dbhelper;

public class ReadCSV {
	 public static void main(int tid,String org,String proj,int course_id,int terms_id,String rootpath){
	//δ��װ��CSV�������ݿ⺯��
			//�������ݿ��������
			DbConn db=Dbhelper.getDb();
			long startTime = System.currentTimeMillis();
			 /*File file = new File("./");
			 File rFile = new File(file.getAbsolutePath()+"/src/json/result.csv");
			System.out.print(rFile);*/
			//File file = new File("E:/eclipseWorkspace/.metadata/allProjUpload/customizableSys/git/src/json/result.csv");
			File file = new File(rootpath+"/github/"+org+"/"+proj+"/src/json/result.csv");
			System.out.print(file);
			try {
			Scanner in = new Scanner(file);
	 		
			System.out.println("���ݿ����ӳɹ�");
			//�жϱ��Ƿ���ڣ��������򴴽���,ÿ����ʦһ����
			String tablename="reposdate";
			
			dao.CreateTable.main(tablename);
			//¼������
			String regEx="[^0-9]";	//������ʽ,ȥ���ļ�ǰ׺������ѧ��
			in.next();
			while (in.hasNext())
			{
				String temp1 = in.nextLine();		//���Ե�һ��
				String[] temp = temp1.split(",");
				
				//System.out.println("temp="+temp);
				if (temp.length < 14)
					continue;
	 
				if (temp.length == 14)
				{	
					Pattern p= Pattern.compile(regEx);
					Matcher m=p.matcher(temp[1]);
					String strNum=m.replaceAll("").trim();	
					boolean bl=db.checkTrue("select id from "+tablename+" where Num='"+strNum+"' and org='" +org+"' and proj='"+proj+"' and terms_id="+terms_id+" and course_id="+course_id+" and tid="+tid);
					if(bl){
						System.out.print("�ļ�¼�Ѵ��ڣ�ִ�и��²���");
						System.out.println();
						int bls=db.executeUpdate("update "+ tablename+" set Login='"+temp[0]+"',IssueNumber='"+temp[2]+"',IssueCount='"+temp[3]+"',IssueLabels='"+temp[4]+"',Events='"+temp[5]+"',FirstTime='"+temp[6]+"',Pulls="+temp[7]+",Commits="+temp[8]+",Additions="+temp[9]+",Deletions="+temp[10]+",ChangedFiles="+temp[11]+",Comments="+temp[12]+",ReviewComments="+temp[13]+" where Num='"+strNum+"' and org='" +org+"' and proj='"+proj+"' and terms_id="+terms_id+" and course_id="+course_id+" and tid="+tid);
						if(!(bls>0)){
							//System.out.print("����ʧ��");
							//System.out.println();
						}
					}else {
						//System.out.print("ִ�в������ݲ���");
						//System.out.println();
						int bls=db.executeUpdate("insert into "+tablename+" (Login,Num,IssueNumber,IssueCount,IssueLabels,Events,FirstTime,Pulls,Commits,Additions,Deletions,ChangedFiles,Comments,ReviewComments,org,proj,terms_id,course_id,tid) values('"+temp[0]+"','"+strNum+"','"+temp[2]+"','"+temp[3]+"','"+temp[4]+"','"+temp[5]+"','"+temp[6]+"','"+temp[7]+"','"+temp[8]+"','"+temp[9]+"','"+temp[10]+"','"+temp[11]+"','"+temp[12]+"',"+temp[13]+",'"+org+"','"+proj+"',"+terms_id+","+course_id+","+tid+")");
						if(!(bls>0)){
							//System.out.print("��������ʧ��");
							//System.out.println();
						}
					}
				}
			}
				

	 
			long EndTime = System.currentTimeMillis();
			long time = (EndTime - startTime) / 1000;
	 
			System.out.println("�������ݹ���ʱ��" + time);
			in.close();
		 }catch (Exception ex) {			 
			      System.out.print("��ȡ���ݳ���");
		 }
			 
	        
	 }
}
