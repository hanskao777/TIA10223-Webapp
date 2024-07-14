package com.venuearea.model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import jdbcinfo.JDBCinfo;

public class VenueAreaDAOImpl implements VenueAreaDAO {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TIA102G5");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO venuearea(venueID,areaName,areaPicture) VALUES (?,?,?)";
	private static final String GET_ALL_STMT = "SELECT venueAreaID,venueID,areaName,areaPicture FROM venuearea order by venueAreaID";
	private static final String GET_ONE_STMT = "SELECT venueAreaID,venueID,areaName,areaPicture FROM venuearea where venueAreaID = ?";
	private static final String DELETE = "DELETE FROM venuearea where venueAreaID = ?";
	private static final String UPDATE = "UPDATE venuearea SET venueID=?, areaName=?, areaPicture=? where venueAreaID = ?";

	@Override
	public void insert(VenueArea venueArea) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, venueArea.getVenueID());
			pstmt.setString(2, venueArea.getAreaName());
			pstmt.setBytes(3, venueArea.getAreaPicture());
			pstmt.toString();
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(VenueArea venueArea) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, venueArea.getVenueID());
			pstmt.setString(2, venueArea.getAreaName());
			pstmt.setBytes(3, venueArea.getAreaPicture());
			pstmt.setInt(4, venueArea.getVenueAreaID());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void delete(Integer venueAreaID) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, venueAreaID);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public VenueArea findbyprimarykey(Integer venueAreaID) {
		VenueArea venueArea = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, venueAreaID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				venueArea = new VenueArea();
				venueArea.setVenueAreaID(rs.getInt("venueAreaID"));
				venueArea.setVenueID(rs.getInt("venueID"));
				venueArea.setAreaName(rs.getString("areaName"));
				venueArea.setAreaPicture(rs.getBytes("areaPicture"));
			}

			pstmt.toString();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return venueArea;
	}

	@Override
	public List<VenueArea> getAll() {
		VenueArea venueArea = null;
		ResultSet rs = null;
		List<VenueArea> list = new ArrayList<VenueArea>();
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				venueArea = new VenueArea();
				venueArea.setVenueAreaID(rs.getInt("venueAreaID"));
				venueArea.setVenueID(rs.getInt("venueID"));
				venueArea.setAreaName(rs.getString("areaName"));
				venueArea.setAreaPicture(rs.getBytes("areaPicture"));
				list.add(venueArea);
			}

			pstmt.toString();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

		return list;
	}

	public static void main(String[] args) {

//		 =========================新增=========================
//		VenueAreaDAOImpl dao = new VenueAreaDAOImpl();
//		VenueArea venueArea = new VenueArea();
//		byte[] areaPicture;
//		venueArea.setVenueID(1);
//		venueArea.setAreaName("NA");
//		FileInputStream fis = null;
//		BufferedInputStream bis = null;
//		try {
//			fis = new FileInputStream("C:/Users/T14 Gen 3/Desktop/圖片/cat.jpg");
//			bis = new BufferedInputStream(fis);
//			areaPicture = new byte[bis.available()];
//			bis.read(areaPicture);
//			venueArea.setAreaPicture(areaPicture);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException ie) {
//			// TODO Auto-generated catch block
//			ie.printStackTrace();
//		} finally {
//			if (bis != null) {
//				try {
//					bis.close();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//			if (fis != null) {
//				try {
//					fis.close();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
//		dao.insert(venueArea);
//		 =========================新增=========================

//		 =========================修改=========================
//		VenueAreaDAOImpl dao = new VenueAreaDAOImpl();
//		VenueArea venueArea = new VenueArea();
//		byte[] areaPicture;
//		venueArea.setVenueAreaID(1);
//		venueArea.setVenueID(1);
//		venueArea.setAreaName("NA");
//		FileInputStream fis = null;
//		BufferedInputStream bis = null;
//		try {
//			fis = new FileInputStream("C:/Users/T14 Gen 3/Desktop/圖片/cat.jpg");
//			bis = new BufferedInputStream(fis);
//			areaPicture = new byte[bis.available()];
//			bis.read(areaPicture);
//			venueArea.setAreaPicture(areaPicture);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException ie) {
//			// TODO Auto-generated catch block
//			ie.printStackTrace();
//		} finally {
//			if (bis != null) {
//				try {
//					bis.close();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//			if (fis != null) {
//				try {
//					fis.close();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
//		dao.update(venueArea);
//		 =========================修改=========================

//		 =========================刪除=========================
//		VenueAreaDAOImpl dao = new VenueAreaDAOImpl();
//		dao.delete(1);
//		 =========================刪除=========================

//		 =========================查詢單一資料=========================
//		VenueAreaDAOImpl dao = new VenueAreaDAOImpl();
//		VenueArea venueArea = dao.findbyprimarykey(7);
//		System.out.println("venueAreaID = " + venueArea.getVenueAreaID());
//		System.out.println("venueID = " + venueArea.getVenueID());
//		System.out.println("AreaName = " + venueArea.getAreaName());
//		byte[] areaPicture = venueArea.getAreaPicture();
//		
//		FileOutputStream fis = null;
//		BufferedOutputStream bis = null;
//		try {
//			fis = new FileOutputStream("C:/Users/T14 Gen 3/Desktop/圖片/dog.jpg");
//			bis = new BufferedOutputStream(fis);
//			bis.write(areaPicture);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException ie) {
//			// TODO Auto-generated catch block
//			ie.printStackTrace();
//		} finally {
//			if (bis != null) {
//				try {
//					bis.close();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//			if (fis != null) {
//				try {
//					fis.close();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
//		 =========================查詢單一資料=========================

//		 =========================查詢全部=========================
		VenueAreaDAOImpl dao = new VenueAreaDAOImpl();
		List<VenueArea> list = dao.getAll();

		for (VenueArea venueArea : list) {
			System.out.print("venueAreaID = " + venueArea.getVenueAreaID() + "||");
			System.out.print("venueID = " + venueArea.getVenueID() + "||");
			System.out.println("AreaName = " + venueArea.getAreaName());
			byte[] areaPicture = venueArea.getAreaPicture();

			FileOutputStream fis = null;
			BufferedOutputStream bis = null;
			if (areaPicture != null) {

				try {
					fis = new FileOutputStream(
							"C:/Users/T14 Gen 3/Desktop/圖片/dog" + venueArea.getVenueAreaID() + ".jpg");
					bis = new BufferedOutputStream(fis);
					bis.write(areaPicture);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException ie) {
					// TODO Auto-generated catch block
					ie.printStackTrace();
				} finally {
					if (bis != null) {
						try {
							bis.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					if (fis != null) {
						try {
							fis.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			} else {
				System.out.println("資料" + venueArea.getVenueAreaID() + "沒有圖片");
			}
		}

//		 =========================查詢全部=========================

	}
}
