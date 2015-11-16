package com.theironyard;

import com.theironyard.entities.Job;
import com.theironyard.entities.Message;
import com.theironyard.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by alhanger on 11/12/15.
 */
@Controller
public class CrudSpringController {

    @Autowired
    UserRepository users;

    @Autowired
    JobRepository jobs;

    @Autowired
    MessageRepository messages;


    @RequestMapping("/")
    public String home(Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");

        if (username == null) {
            return "login";
        }

        model.addAttribute("user", users.findOneByUsername(username));
        model.addAttribute("users", users.findAll());

        if (messages.count() != 0) {
            model.addAttribute("inbox", users.findOneByUsername(username).inbox);
        }
        //model.addAttribute("friends", users.findOneByName(username).friends);

        return "home";
    }

    @RequestMapping("/login")
    public String login(String username, String password, HttpSession session) throws Exception {
        session.setAttribute("username", username);

        User user = users.findOneByUsername(username);

        if (user == null) {
            return "create-account";
        }
        else if (!PasswordHash.validatePassword(password, user.password)) {
            throw new Exception("Incorrect Password");
        }

        return "redirect:/";
    }

    @RequestMapping("/create-account")
    public String createAccount(
            String username,
            String password,
            String firstName,
            String lastName,
            String location,
            int age,
            String music,
            String vacation,
            String company,
            String position,
            int yearsWorked
    ) throws InvalidKeySpecException, NoSuchAlgorithmException {
        User user = new User();
        user.username = username;
        user.password = PasswordHash.createHash(password);
        user.firstName = firstName;
        user.lastName = lastName;
        user.location = location;
        user.age = age;
        user.music = music;
        user.vacation = vacation;

        users.save(user);

        Job job = new Job();
        job.company = company;
        job.position = position;
        job.yearsWorked = yearsWorked;
        job.user = user;

        jobs.save(job);

        return "/";
    }

    @RequestMapping("/pm")
    public String sendPM(Model model, Integer id) {
        model.addAttribute("id", id);
        return "send-message";
    }

    @RequestMapping("/send-message")
    public String sendMessage(HttpSession session, Integer id, String message) {
        String username = (String) session.getAttribute("username");
        Message newMsg = new Message();
        newMsg.content = message;

        User sender = users.findOneByUsername(username);
        sender.outbox.add(newMsg);
        newMsg.sender = sender;
        users.save(sender);

        User temp = users.findOneById(id);
        temp.inbox.add(newMsg);
        newMsg.receiver = temp;
        users.save(temp);

        messages.save(newMsg);

        return "redirect:/";
    }

    @RequestMapping("/reply")
    public String replyMessage(Model model, Integer id) {
        model.addAttribute("id", id);
        return "send-message";
    }

    @RequestMapping("/delete-message")
    public String deleteMessage(HttpSession session, Integer id) {
        String username = (String) session.getAttribute("username");
        Message deleteMsg = messages.findOneById(id);
        User user = users.findOneByUsername(username);
        user.inbox.remove(deleteMsg);

        return "redirect:/";
    }

    @RequestMapping("/info")
    public String personProfile(Model model, Integer id) {
        User user = users.findOneById(id);

        model.addAttribute("user", user);

        return "individual";
    }

    @RequestMapping("/return")
    public String returnHome() {
        return "redirect:/";
    }

//    @RequestMapping("/add-friend")
//    public String addFriend(HttpSession session, Integer id) {
//        String username = (String) session.getAttribute("username");
//        User user1 = users.findOneByName(username);
//        User user2 = users.findOneById(id);
//        user1.friends.add(user2);
//
//        friends.save(user2);
//
//        return "redirect:/";
//    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
