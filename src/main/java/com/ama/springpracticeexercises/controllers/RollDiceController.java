package com.ama.springpracticeexercises.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RollDiceController {

    @GetMapping("/roll-dice/{n}")
    public String rollDice(@PathVariable int n, Model model){
        model.addAttribute("n", n);
        int max = 6;
        int min = 1;
        int range = (max - min) + 1;

        for (int i = 0; i < 6; i++) {
            int random = (int) (Math.random() * range) + min;
            if (random != n) {
                return "Sorry, you guessed wrong.";
            }
        }
            return "rollDice";
    }
}

