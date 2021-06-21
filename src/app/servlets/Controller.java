package app.servlets;

import app.entities.Comment;
import app.entities.Tender;
import app.entities.User;
import app.model.TenderModel;
import app.model.UserModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/do/*")
public class Controller extends HttpServlet {

    private static User CurrentUser;
    final int MAX_TENDER_NAME_LENGTH = 50;
    final int MAX_DESC_LENGTH = 200;
    final int MAX_TAG_LENGTH = 10;
    final int MAX_TAG_AMOUNT = 20;
    final int MAX_COMMENT_LENGTH = 5000;
    final int MAX_LOGIN_LENGTH = 20;
    final int MIN_LOGIN_LENGTH = 4;
    final int MAX_PASSWORD_LENGTH = 20;
    final int MIN_PASSWORD_LENGTH = 4;
    final String[] RESTRICTED_SYMBOLS = {"/","@","!","$","%","*"," "};
    final String[] RESTRICTED_TENDER_SYMBOLS = {"/","@","!","$","%","*"};

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (CurrentUser == null) {
            switch (pathInfo) {
                case "/list":
                case "/":
                    list(req, resp);
                    break;
                case "/view":
                    view(req, resp);
                    break;
                case "/login":
                    login(req, resp);
                    break;
                case "/registration":
                    registration(req, resp);
                    break;
                case "/find":
                    find(req, resp);
                    break;
                default:
                    defMain(req, resp);
                    break;
            }
        } else {
            switch (pathInfo) {
                case "/list":
                case "/":
                    list(req, resp);
                    break;
                case "/add":
                    add(req, resp);
                    break;
                case "/view":
                    view(req, resp);
                    break;
                case "/login":
                    login(req, resp);
                    break;
                case "/registration":
                    registration(req, resp);
                    break;
                case "/find":
                    find(req, resp);
                    break;
                case "/logout":
                    logout(req, resp);
                    break;
                case "/owned":
                    ownedTenders(req, resp);
                    break;
                case "/delete":
                    delete(req, resp);
                    break;
                case "/startTender":
                    startTender(req, resp);
                    break;
                case "/stopTender":
                    stopTender(req, resp);
                    break;
                default:
                    defMain(req, resp);
                    break;
            }
        }
    }
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TenderModel tenderModel = TenderModel.getInstance();
        List<String> names = tenderModel.listOfTenderNames();
        List<Tender> tenders = new ArrayList<>(tenderModel.getModel().values());
        req.setAttribute("userNames", names);
        req.setAttribute("pullOfTenders", tenders);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/find.jsp");
        requestDispatcher.forward(req, resp);
    }

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tenderName = req.getParameter("tender");
        String description = req.getParameter("description");
        String tag = req.getParameter("tags");
        TenderModel tenderModel = TenderModel.getInstance();
        if((tenderName != null && description != null)&&(!tenderName.equals("") && !description.equals(""))) {
            String[] buffTenderName = tenderName.split("");
            String[] buffDesc = tenderName.split("");
            if (stringCheck(tenderName, RESTRICTED_TENDER_SYMBOLS) && buffTenderName.length < MAX_TENDER_NAME_LENGTH && buffDesc.length < MAX_DESC_LENGTH) {
                Tender tender = new Tender(tenderName, description);
                tender.setTenderOwner(CurrentUser);
                tender.setTenderId(tender.hashCode());
                String[] arrayOfTags = tag.split(" ");
                if (arrayOfTags.length < MAX_TAG_AMOUNT) {
                    for (String buffTag : arrayOfTags) {
                        if (buffTag.length() < MAX_TAG_LENGTH) {
                            tender.addTenderTag(buffTag);
                        }
                    }
                }
                tenderModel.add(tender.getTenderId(), tender);
                req.setAttribute("tenderAdded", true);
            } else {
                req.setAttribute("tenderAdded", false);
            }
        } else {
            req.setAttribute("tenderAdded", false);
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/add.jsp");
        requestDispatcher.forward(req, resp);
    }

    protected void view(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String stringTenderId = req.getParameter("id");
        String commentOfTender = req.getParameter("comment");
        int tenderId = Integer.parseInt(stringTenderId);

        TenderModel tenderModel = TenderModel.getInstance();
        Tender tender = tenderModel.getModelElement(tenderId);
        if(commentOfTender != null && !commentOfTender.equals("") && CurrentUser!= null && commentOfTender.length() < MAX_COMMENT_LENGTH) {
            Comment comment = new Comment(commentOfTender, CurrentUser);
            tender.getTenderComments().add(comment);
        }
        req.setAttribute("list", tender.getTenderComments());
        req.setAttribute("Tender", tender);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/view.jsp");
        requestDispatcher.forward(req, resp);
    }

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("pass");
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        UserModel UsersModel = UserModel.getInstance();

        if (name != null && password != null){
            if (!password.equals("") && !name.equals("")){
                if (!UsersModel.getModel().isEmpty() && (UsersModel.getModelElement(user.getName()).getName().equals(name) && UsersModel.getModelElement(user.getName()).getPassword().equals(password))){
                    CurrentUser = user;
                    req.setAttribute("logged", " Logged ");
                    RequestDispatcher requestDispatcher = req.getRequestDispatcher("/index.jsp");
                    requestDispatcher.forward(req, resp);
                } else {
                    req.setAttribute("logged", " Not Logged");
                }
            }
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/login.jsp");
        requestDispatcher.forward(req, resp);
    }

    protected void registration(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("pass");
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        UserModel UsersModel = UserModel.getInstance();
        if (name != null && password != null){
            if (((name.length() > MIN_LOGIN_LENGTH && name.length() <= MAX_LOGIN_LENGTH)&&(password.length() > MIN_PASSWORD_LENGTH && password.length() < MAX_PASSWORD_LENGTH)) && UsersModel.getModelElement(name) == null){
                if (stringCheck(name, RESTRICTED_SYMBOLS) && stringCheck(password, RESTRICTED_SYMBOLS)) {
                    req.setAttribute("add", "User added");
                    UsersModel.add(name, user);
                    req.setAttribute("userName", name);
                    CurrentUser = user;
                    RequestDispatcher requestDispatcher = req.getRequestDispatcher("/index.jsp");
                    requestDispatcher.forward(req, resp);
                } else {
                    req.setAttribute("add", "User not added");
                }
            } else if (!password.equals("") && !name.equals("")){
                req.setAttribute("add", "User not added");
            }
        }

        req.setAttribute("userName", name);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/registration.jsp");
        requestDispatcher.forward(req, resp);
    }
    protected void find(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tag = req.getParameter("tag");
        TenderModel tenderModel = TenderModel.getInstance();
        List<Tender> tenders = new ArrayList<>(tenderModel.getModel().values());
        List<Tender> foundTenders = new ArrayList<>();
        if(tag != null && !tag.equals("")){
            for (Tender td: tenders) {
                for (String buffTag : td.getTenderTags()){
                    if (buffTag.equals(tag)){
                        foundTenders.add(td);
                    }
                }
            }
        }
        req.setAttribute("pullOfTenders", foundTenders);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/find.jsp");
        requestDispatcher.forward(req, resp);
    }

    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CurrentUser = null;
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/index.jsp");
        requestDispatcher.forward(req, resp);
    }

    protected void defMain(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/index.jsp");
        requestDispatcher.forward(req, resp);
    }

    protected void ownedTenders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TenderModel tenderModel = TenderModel.getInstance();
        List<Tender> tenders = new ArrayList<>(tenderModel.getModel().values());
        List<Tender> foundTenders = new ArrayList<>();
        for (Tender td: tenders) {
            if(td.getTenderOwner().equals(CurrentUser)){
                foundTenders.add(td);
            }
        }
        req.setAttribute("pullOfTenders", foundTenders);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/find.jsp");
        requestDispatcher.forward(req, resp);
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        TenderModel tenderModel = TenderModel.getInstance();
        int tenderID = (int) session.getAttribute("CurrentTenderID");
        tenderModel.remove(tenderID);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/do/owned");
        requestDispatcher.forward(req, resp);
    }

    protected void startTender(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        TenderModel tenderModel = TenderModel.getInstance();
        int tenderID = (int) session.getAttribute("CurrentTenderID");
        tenderModel.getModelElement(tenderID).setOnline();
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("#");
        requestDispatcher.forward(req, resp);
    }

    protected void stopTender(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        TenderModel tenderModel = TenderModel.getInstance();
        int tenderID = (int) session.getAttribute("CurrentTenderID");
        tenderModel.getModelElement(tenderID).setOffline();
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("#");
        requestDispatcher.forward(req, resp);
    }

    protected boolean stringCheck(String word, String[] restrictions){
        if (word != null && restrictions != null) {
            for (String buff : restrictions) {
                if (word.contains(buff)){
                    return false;
                }
            }
        }
        return true;
    }

    public static User getCurrentUser(){return CurrentUser;}

}