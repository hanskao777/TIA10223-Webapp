package com.venue.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.venuearea.model.VenueArea;
import com.venuearea.model.VenueAreaService;

@WebServlet("/venueArea/venueArea.do")
public class VenueAreaServlet extends HttpServlet {

	private Connection con;

	public void init() throws ServletException {
		try {
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TIA102G5");
			con = ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

	public void destroy() {
		try {
			if (con != null)
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("venueAreaID");
			if (str == null || str.trim().length() == 0) {// 請求如果是空的或是空格
				errorMsgs.add("請輸入場館區域編號");
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/venuearea/select_page.jsp");
				failureView.forward(req, res);
				return;
			}

			Integer venueAreaID = null;
			try {
				venueAreaID = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("場地區域編號格式不正確");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/venuearea/select_page.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始查詢資料 *****************************************/
			VenueAreaService venueAreaSvc = new VenueAreaService();
			VenueArea venueArea = venueAreaSvc.getOneVenueArea(venueAreaID);

			if (venueArea == null) {
				errorMsgs.add("查無資料");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/venuearea/select_page.jsp");
				failureView.forward(req, res);
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("venueArea", venueArea);

			// 處理圖片顯示
			res.setContentType("image/gif");
			ServletOutputStream out = res.getOutputStream();
			try {
				Statement stmt = con.createStatement();
				ResultSet rs = stmt
						.executeQuery("SELECT areaPicture FROM venuearea WHERE venueAreaID = " + venueAreaID);

				if (rs.next()) {
					BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("areaPicture"));
					byte[] buf = new byte[4 * 1024]; // 4K buffer
					int len;
					while ((len = in.read(buf)) != -1) {
						out.write(buf, 0, len);
					}
					in.close();
				} else {
					InputStream in = getServletContext().getResourceAsStream("/TIA102G5/back-end/venuearea/images/none2.jpg");
					byte[] b = new byte[in.available()];
					in.read(b);
					out.write(b);
					in.close();
				}
				rs.close();
				stmt.close();
			} catch (Exception e) {
				InputStream in = getServletContext().getResourceAsStream("/TIA102G5/back-end/venuearea/images/null.jpg");
				byte[] b = in.readAllBytes();
				out.write(b);
				in.close();
			}
//            =================/處理圖片顯示================

			String url = "/back-end/venuearea/listOneVenueArea.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		if ("getOne_For_Update".equals(action)) { // 來自listAllVenueArea.jsp的請求，按下修改按鍵
			// 跳轉到修改頁面，並且可以進行更新
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer venueAreaID = Integer.valueOf(req.getParameter("venueAreaID"));

			/*************************** 2.開始查詢資料 ****************************************/
			VenueAreaService venueAreaSvc = new VenueAreaService();
			VenueArea venueArea = venueAreaSvc.getOneVenueArea(venueAreaID);
			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("venueArea", venueArea); // 資料庫取出的venueArea物件,存入req
			String url = "/back-end/venuearea/update_venueArea_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_venueArea_input.jsp
			successView.forward(req, res);
		}
//		
//		
		if ("update".equals(action)) { // 來自update_venueArea_input.jsp的請求
//			
			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
//		
//				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			Integer venueAreaID = Integer.valueOf(req.getParameter("venueAreaID").trim());
//				
			String str = req.getParameter("venueID");
			if (str == null || str.trim().length() == 0) {// 請求如果是空的或是空格
				errorMsgs.add("請輸入場館編號");
			}

			Integer venueID = null;
			try {
				venueID = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("場地編號格式不正確");
			}

			if (venueID > 3) {
				errorMsgs.add("場館編號目前只有1~3");
			}

			String areaName = req.getParameter("areaName").trim();
			String areaNameReg = "^[A-Z][A-Z]$";
			if (areaName == null || areaName.trim().length() == 0) {
				errorMsgs.add("職位請勿空白");
			} else if (!areaName.trim().matches(areaNameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("場館名稱: 目前只有兩個大寫英文字母組成");
			}
//				
//				java.sql.Date hiredate = null;
//				try {
//hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
//				} catch (IllegalArgumentException e) {
//					hiredate=new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.add("請輸入日期!");
//				}
//
//				Double sal = null;
//				try {
//sal = Double.valueOf(req.getParameter("sal").trim());
//				} catch (NumberFormatException e) {
//					sal = 0.0;
//					errorMsgs.add("薪水請填數字.");
//				}
//
//				Double comm = null;
//				try {
//comm = Double.valueOf(req.getParameter("comm").trim());
//				} catch (NumberFormatException e) {
//					comm = 0.0;
//					errorMsgs.add("獎金請填數字.");
//				}
//
//Integer deptno = Integer.valueOf(req.getParameter("deptno").trim());
//
			VenueArea venueArea = new VenueArea();
			venueArea.setVenueAreaID(venueAreaID);
			venueArea.setVenueID(venueID);
			venueArea.setAreaName(areaName);
//				empVO.setJob(job);
//				empVO.setHiredate(hiredate);
//				empVO.setSal(sal);
//				empVO.setComm(comm);
//				empVO.setDeptno(deptno);
//
//				// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("venueArea", venueArea); // 含有輸入格式錯誤的venueArea物件,也存入req
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/venuearea/update_venueArea_input.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}
//				
//				/***************************2.開始修改資料*****************************************/
			VenueAreaService venueAreaSvc = new VenueAreaService();
			venueArea = venueAreaSvc.updateVenueArea(venueAreaID, venueID, areaName, null);
//				
//				/***************************3.修改完成,準備轉交(Send the Success view)*************/
			req.setAttribute("venueArea", venueArea); // 資料庫update成功後,正確的的venueArea物件,存入req
			String url = "/back-end/venuearea/listOneVenueArea.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneVenueArea.jsp
			successView.forward(req, res);
		}
//
		if ("insert".equals(action)) { // 來自addVenueArea.jsp的請求
//			
			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
//
//				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
			String str = req.getParameter("venueID");
			if (str == null || str.trim().length() == 0) {// 請求如果是空的或是空格
				errorMsgs.add("請輸入場館編號");
			}

			Integer venueID = null;
			try {
				venueID = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("場地編號格式不正確");
			}

			if (venueID > 3) {
				errorMsgs.add("場館編號目前只有1~3");
			}

			String areaName = req.getParameter("areaName").trim();
			String areaNameReg = "^[A-Z][A-Z]$";
			if (areaName == null || areaName.trim().length() == 0) {
				errorMsgs.add("職位請勿空白");
			} else if (!areaName.trim().matches(areaNameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("場館名稱: 目前只有兩個大寫英文字母組成");
			}

//				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				if (ename == null || ename.trim().length() == 0) {
//					errorMsgs.add("員工姓名: 請勿空白");
//				} else if(!ename.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//	            }
//				
//String job = req.getParameter("job").trim();
//				if (job == null || job.trim().length() == 0) {
//					errorMsgs.add("職位請勿空白");
//				}
//				
//				java.sql.Date hiredate = null;
//				try {
//hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
//				} catch (IllegalArgumentException e) {
//					hiredate=new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.add("請輸入日期!");
//				}
//				
//				Double sal = null;
//				try {
//sal = Double.valueOf(req.getParameter("sal").trim());
//				} catch (NumberFormatException e) {
//					sal = 0.0;
//					errorMsgs.add("薪水請填數字.");
//				}
//				
//				Double comm = null;
//				try {
//comm = Double.valueOf(req.getParameter("comm").trim());
//				} catch (NumberFormatException e) {
//					comm = 0.0;
//					errorMsgs.add("獎金請填數字.");
//				}
//				
//Integer deptno = Integer.valueOf(req.getParameter("deptno").trim());
//
			VenueArea venueArea = new VenueArea();
			venueArea.setVenueID(venueID);
			venueArea.setAreaName(areaName);
//				empVO.setHiredate(hiredate);
//				empVO.setSal(sal);
//				empVO.setComm(comm);
//				empVO.setDeptno(deptno);
//
//				// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("venueArea", venueArea); // 含有輸入格式錯誤的venueArea物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/venueArea/addVenueArea.jsp");
				failureView.forward(req, res);
				return;
			}
//				
//				/***************************2.開始新增資料***************************************/
			VenueAreaService venueAreaSvc = new VenueAreaService();
			venueArea = venueAreaSvc.addVenueArea(venueID, areaName, null);
//				
//				/***************************3.新增完成,準備轉交(Send the Success view)***********/
			String url = "/back-end/venuearea/listAllVenueArea.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllVenueArea.jsp
			successView.forward(req, res);
		}
//		
//		
		if ("delete".equals(action)) { // 來自listAllVenueArea.jsp
//
			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
//	
//				/***************************1.接收請求參數***************************************/
			Integer venueAreaID = Integer.valueOf(req.getParameter("venueAreaID"));
//				
//				/***************************2.開始刪除資料***************************************/
			VenueAreaService venueAreaSvc = new VenueAreaService();
			venueAreaSvc.deleteVenueArea(venueAreaID);
//				
//				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
			String url = "/back-end/venuearea/listAllVenueArea.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}
	}
}
