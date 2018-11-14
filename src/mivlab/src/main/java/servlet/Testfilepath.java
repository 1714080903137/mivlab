package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wm.utils.DbConn;
import com.wm.utils.GetList;

import bean.GitDate;
import bean.ScoreProportion;
import utils.Dbhelper;

/**
 * Servlet implementation class Testfilepath
 */
@WebServlet("/Testfilepath")
public class Testfilepath extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private static final String UPLOAD_DIRECTORY = "upload";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Testfilepath() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		/*�洢GitHub���ݵ�·������-begin*/
		 File file = new File("./");
		 file.getCanonicalPath();
		 String path= file.getCanonicalPath();
		 path=path+"/src/json/";
	     System.out.print(path);
		 //gitrepostats.ReadCSV.main(1,"org","proj",1,1);
		// System.out.print("ok");
		 //System.out.println();
		 /*�洢GitHub���ݵ�·������-end */
		 
		 /*�洢����ǰ��ĿĿ¼�� ·������-begin	*/		
		//String uploadPath = request.getServletContext().getRealPath("./") + File.separator + UPLOAD_DIRECTORY;
	    /* String uploadPath = request.getServletContext().getRealPath("./") + File.separator + UPLOAD_DIRECTORY;
		 System.out.print("uploadPath="+uploadPath+"****");
		 System.out.println();*/
		 
		//ȡ�ø�Ŀ¼·��
		 /*
		 String realPath=getServletContext().getRealPath("/");//��Ŀ����·��

		 
		 System.out.print("realPath="+realPath+"****");
		 System.out.println();
		 */
	     
	     //����ץȡԭ����*������
		/* String[] a= {};
		    gitrepostats.App.main(a);
		    System.out.print("ִ��APP");
		   */ 
		    
	     //����Ϊ����ֻ����ѧ��
	     
	   //δ��װ��CSV�������ݿ⺯��
			//�������ݿ��������
	     	
			DbConn db=Dbhelper.getDb();
			long startTime = System.currentTimeMillis();
			 File file1 = new File("./");
			 File rFile = new File(file1.getAbsolutePath()+"/src2018Android/json/result.csv");
			
			System.out.print(rFile);
			try {
			Scanner in = new Scanner(rFile);
	 		
			System.out.println("���ݿ����ӳɹ�");
			//�жϱ��Ƿ���ڣ��������򴴽���,ÿ����ʦһ����
			//String tablename="reposdate";
			
			//dao.CreateTable.main(tablename);
			//¼������
			String regEx="[^0-9]";	//������ʽ
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
					String str=m.replaceAll("").trim();	
					System.out.print(str);
					System.out.println();
					int bls=db.executeUpdate("insert into students (sno,sname,sgender,classinfo_id,sphone,spassword) values('"+str+"','ѧ����','��','4','123','222')");
				}
			}
				

	 
			long EndTime = System.currentTimeMillis();
			long time = (EndTime - startTime) / 1000;
	 
			System.out.println("�������ݹ���ʱ��" + time);
			in.close();
		 }catch (Exception ex) {			 
			      System.out.print("��ȡ���ݳ���");
		 }
	     
	     
	     //����Ϊ����ֻ����ѧ��
		    
		 //���²���ϵ��ѧ�������Ƿ�¼��ϵͳ
	     /*
		  //�������ݿ��������
			DbConn db=Dbhelper.getDb();
			//��ȡsession����
			HttpSession session=request.getSession();
		  //������д�����ݿ�
		    int tid=1;
		    String org="hzuapps";
		    String proj="android-labs-2018";
		    String rootpath="null";
		    gitrepostats.ReadCSV2.main(tid,org,proj,rootpath);	//��ȡʵ������
		    		    		    
		    //ͳ�Ƴɼ�
		    //pullռ�ȣ����ִ���
		    String pulls="0.4";
			String pullsFull="6";
			//commitsռ�ȣ����ִ���
		    String commits="0.4";
			String commitsFull="6";			
			//pullռ�ȣ����ִ���
		    String files="0.1";
			String filesFull="6";		
			//pullռ�ȣ����ִ���
		    String comments="0.1";
			String commentsFull="0";
		    PrintWriter out=response.getWriter();
			
		    //ʵ������ֵ����
		   
		    ScoreProportion sp=new ScoreProportion();
		    sp.setPulls(Double.parseDouble(pulls));
		    sp.setCommits(Double.parseDouble(commits));
		    sp.setComments(Double.parseDouble(comments));
		    sp.setFile(Double.parseDouble(files));
		    sp.setPullsFull(Integer.parseInt(pullsFull));
		    sp.setCommitsFull(Integer.parseInt(commitsFull));
		    sp.setFileFull(Integer.parseInt(filesFull));
		    sp.setCommentsFull(Integer.parseInt(commentsFull));
		    
		    //��ȡ��ѯ���ʵ�����ݣ�ʵ����
		    String tablename="outreposdate";
		    String sql="select id,Pulls,Commits,ChangedFiles,Comments from "+tablename+" where org='"+org+"' and proj='"+proj+"'";
		    List<GitDate> gitdatelist=GetList.getlist(GitDate.class, db.executeQuery(sql));
		    System.out.print("��ѯ���="+sql);
		    System.out.println();
		    System.out.print("���ݳ���"+gitdatelist.size());
		    int flag=0;  //���Գɼ����������
		    for(int i=0;i<gitdatelist.size();i++) {
		    	double sum=0;
		    	int p=gitdatelist.get(i).getPulls();
		    	int co=gitdatelist.get(i).getComments();
		    	int ci=gitdatelist.get(i).getCommits();
		    	int f=gitdatelist.get(i).getChangedFiles();
		    	
		    	//���ִ���
		    	int pf=sp.getPullsFull();
		    	int cif=sp.getCommitsFull();
		    	int cof=sp.getCommentsFull();
		    	int ff=sp.getFileFull();
		    	
		    	//��ֵ����
		    	double pull2=sp.getPulls();
		    	double commit2=sp.getCommits();
		    	double file2=sp.getFile();
		    	double comment2=sp.getComments();
		    	
		    	DecimalFormat df=new DecimalFormat("0.0");
		    	if(p>=pf) {
		    		sum=pull2;	    		
		    	}else {	    		
		    		sum=sum+pull2*p/pf;
		    	}
		    	if(ci>=cif) {
		    		sum=sum+commit2;
		    	}else {
		    		sum=sum+commit2*ci/cif;
		    	}
		    	if(f>=ff) {
		    		sum=sum+file2;
		    	}else {
		    		sum=sum+file2*f/ff;
		    	}
		    	if(co>=cof) {
		    		sum=sum+comment2;
		    	}else {
		    		sum=sum+comment2*co/cof;	    		
		    	}
		    	sum=sum*100;
		    	
		    	System.out.print((int)sum);
		    	System.out.println();
		    	int bls=db.executeUpdate("update  "+tablename+" set score="+(int)sum+" where id="+gitdatelist.get(i).getId());
		    	if(bls<=0){
					flag=1;
				}		    	
		    }
		    
		    /*ɾ���ļ�����
		    String filepath=rootpath+"/github/";
		    if( gitrepostats.DelDirfile.delAllFile(filepath)) {
		    	System.out.print("ɾ���ļ��ɹ���");
		    	if(flag==1) {
					json.put("msg", "������δ���£������²���");
				}else{
					json.put("msg", "�������ݸ������");
				}
		    }
		    ɾ���ļ�����*/
		   
		    
		    
		 //���ϲ���ϵ��ѧ�������Ƿ�¼��ϵͳ
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
