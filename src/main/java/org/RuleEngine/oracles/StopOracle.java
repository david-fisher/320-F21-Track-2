package org.RuleEngine.oracles;

import org.RuleEngine.engine.GameState;

import java.util.ArrayList;
import java.util.Random;

public class StopOracle implements Oracle {
    public Cache cache;
    public StopOracle() {
        this.cache = new Cache(1000000);
    }

    public void processMove(String event, GameState currState) {
        currState.getAllEvents().get(event).get(0).execute(currState);
    }

    public String decideMove(GameState currState, ArrayList<String> prev) {
        int[][] grid = {{0,0,0},{0,0,0},{0,0,0}};
        for (String s: prev) {
            int marker = 0;
            if (s.startsWith("A")) {
                marker = 3;
            } else if(s.startsWith("H")) {
                marker = 5;
            }
            if (s.endsWith("Event")) {
                String s2 = s.substring(1);
                switch(s2) {
                    case "TLEvent":
                        grid[0][0] = marker;
                        break;
                    case "TMEvent":
                        grid[0][1] = marker;
                        break;
                    case "TREvent":
                        grid[0][2] = marker;
                        break;
                    case "MLEvent":
                        grid[1][0] = marker;
                        break;
                    case "MMEvent":
                        grid[1][1] = marker;
                        break;
                    case "MREvent":
                        grid[1][2] = marker;
                        break;
                    case "BLEvent":
                        grid[2][0] = marker;
                        break;
                    case "BMEvent":
                        grid[2][1] = marker;
                        break;
                    case "BREvent":
                        grid[2][2] = marker;
                        break;
                    default:
                        break;
                }
            }
        }
        Random rand = new Random();
        ArrayList<String> keyArray = new ArrayList<String>(currState.getAllEvents().keySet());
        String s = keyArray.get(rand.nextInt(keyArray.size()));
        while (prev.indexOf("A"+s) != -1 || prev.indexOf("H"+s) != -1 || !s.endsWith("Event")) {
            s = keyArray.get(rand.nextInt(keyArray.size()));
        }
        if (grid[0][0] + grid[0][1] + grid[0][2] == 6) {
            if (grid[0][0] == 0) {
                s = "TLEvent";
            } else if (grid[0][1] == 0) {
                s = "TMEvent";
            } else {
                s = "TREvent";
            }
        } else if (grid[1][0] + grid[1][1] + grid[1][2] == 6) {
            if (grid[1][0] == 0) {
                s = "MLEvent";
            } else if (grid[1][1] == 0) {
                s = "MMEvent";
            } else {
                s = "MREvent";
            }
        } else if (grid[2][0] + grid[2][0] + grid[2][0] == 6) {
            if (grid[2][0] == 0) {
                s = "BLEvent";
            } else if (grid[2][1] == 0) {
                s = "BMEvent";
            } else {
                s = "BREvent";
            }
        } else if (grid[0][0] + grid[1][0] + grid[2][0] == 6) {
            if (grid[0][0] == 0) {
                s = "TLEvent";
            } else if (grid[1][0] == 0) {
                s = "MLEvent";
            } else {
                s = "BLEvent";
            }
        } else if (grid[0][1] + grid[1][1] + grid[2][1] == 6) {
            if (grid[0][1] == 0) {
                s = "TMEvent";
            } else if (grid[1][1] == 0) {
                s = "MMEvent";
            } else {
                s = "BMEvent";
            }
        } else if (grid[0][2] + grid[1][2] + grid[2][2] == 6) {
            if (grid[0][2] == 0) {
                s = "TREvent";
            } else if (grid[1][2] == 0) {
                s = "MREvent";
            } else {
                s = "BREvent";
            }
        } else if (grid[0][0] + grid[1][1] + grid[2][2] == 6) {
            if (grid[0][0] == 0) {
                s = "TLEvent";
            } else if (grid[1][1] == 0) {
                s = "MMEvent";
            } else {
                s = "BREvent";
            }
        } else if (grid[0][2] + grid[1][1] + grid[2][0] == 6) {
            if (grid[0][2] == 0) {
                s = "TREvent";
            } else if (grid[1][1] == 0) {
                s = "MMEvent";
            } else {
                s = "BLEvent";
            }
        } else if (grid[0][0] + grid[0][1] + grid[0][2] == 10) {
            if (grid[0][0] == 0) {
                s = "TLEvent";
            } else if (grid[0][1] == 0) {
                s = "TMEvent";
            } else {
                s = "TREvent";
            }
        } else if (grid[1][0] + grid[1][1] + grid[1][2] == 10) {
            if (grid[1][0] == 0) {
                s = "MLEvent";
            } else if (grid[1][1] == 0) {
                s = "MMEvent";
            } else {
                s = "MREvent";
            }
        } else if (grid[2][0] + grid[2][0] + grid[2][0] == 10) {
            if (grid[2][0] == 0) {
                s = "BLEvent";
            } else if (grid[2][1] == 0) {
                s = "BMEvent";
            } else {
                s = "BREvent";
            }
        } else if (grid[0][0] + grid[1][0] + grid[2][0] == 10) {
            if (grid[0][0] == 0) {
                s = "TLEvent";
            } else if (grid[1][0] == 0) {
                s = "MLEvent";
            } else {
                s = "BLEvent";
            }
        } else if (grid[0][1] + grid[1][1] + grid[2][1] == 10) {
            if (grid[0][1] == 0) {
                s = "TMEvent";
            } else if (grid[1][1] == 0) {
                s = "MMEvent";
            } else {
                s = "BMEvent";
            }
        } else if (grid[0][2] + grid[1][2] + grid[2][2] == 10) {
            if (grid[0][2] == 0) {
                s = "TREvent";
            } else if (grid[1][2] == 0) {
                s = "MREvent";
            } else {
                s = "BREvent";
            }
        } else if (grid[0][0] + grid[1][1] + grid[2][2] == 10) {
            if (grid[0][0] == 0) {
                s = "TLEvent";
            } else if (grid[1][1] == 0) {
                s = "MMEvent";
            } else {
                s = "BREvent";
            }
        } else if (grid[0][2] + grid[1][1] + grid[2][0] == 10) {
            if (grid[0][2] == 0) {
                s = "TREvent";
            } else if (grid[1][1] == 0) {
                s = "MMEvent";
            } else {
                s = "BLEvent";
            }
        }
        System.out.println("AI Chooses " + s);
        return s;
    }
}
