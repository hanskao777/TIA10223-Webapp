package com.venue.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/venueArea/getImage")
public class VenueAreaImageServlet extends HttpServlet {

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

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("image/gif");
        ServletOutputStream out = res.getOutputStream();
        String venueAreaID = req.getParameter("venueAreaID");

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT areaPicture FROM venuearea WHERE venueAreaID = " + venueAreaID);

            if (rs.next()) {
                BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("areaPicture"));
                byte[] buf = new byte[4 * 1024]; // 4K buffer
                int len;
                while ((len = in.read(buf)) != -1) {
                    out.write(buf, 0, len);
                }
                in.close();
            } else {
                InputStream in = getServletContext().getResourceAsStream("/back-end/venuearea/images/none2.jpg");
                byte[] b = new byte[in.available()];
                in.read(b);
                out.write(b);
                in.close();
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            InputStream in = getServletContext().getResourceAsStream("/back-end/venuearea/images/null.jpg");
            byte[] b = in.readAllBytes();
            out.write(b);
            in.close();
        }
    }
}