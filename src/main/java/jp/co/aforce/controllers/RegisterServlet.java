package jp.co.aforce.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jp.co.aforce.models.Member;
import jp.co.aforce.services.MemberService;

@WebServlet("/views/register")
public class RegisterServlet extends HttpServlet {

    private MemberService memberService = new MemberService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String loginId = request.getParameter("loginId");
        String password = request.getParameter("password");
        String lastName = request.getParameter("lastName");
        String firstName = request.getParameter("firstName");
        String sex = request.getParameter("sex");
        String birthYear = request.getParameter("birthYear");
        String birthMonth = request.getParameter("birthMonth");
        String birthDay = request.getParameter("birthDay");
        String job = request.getParameter("job");
        String phoneNumber = request.getParameter("phoneNumber");
        String mailAddress = request.getParameter("mailAddress");

        if (loginId == null || loginId.isEmpty()) {
            request.setAttribute("errorMessage", "ユーザIDは必須入力項目です。");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        if (memberService.isMemberExists(loginId)) {
            request.setAttribute("errorMessage", "会員情報が既に登録されています。");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        Member member = new Member();
        member.setMemberId(generateMemberId());
        member.setLoginId(loginId);
        member.setPassword(password);
        member.setLastName(lastName);
        member.setFirstName(firstName);
        member.setSex(sex);
        member.setBirthYear(Integer.parseInt(birthYear));
        member.setBirthMonth(Integer.parseInt(birthMonth));
        member.setBirthDay(Integer.parseInt(birthDay));
        member.setJob(job);
        member.setPhoneNumber(phoneNumber);
        member.setMailAddress(mailAddress);

        if (memberService.registerMember(member)) {
            response.sendRedirect("success.jsp");
        } else {
            request.setAttribute("errorMessage", "登録に失敗しました。再度お試しください。");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    private String generateMemberId() {
        return "MEM" + System.currentTimeMillis();
    }
}
