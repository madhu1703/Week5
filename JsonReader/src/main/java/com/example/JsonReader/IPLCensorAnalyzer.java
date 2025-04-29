package com.example.JsonReader;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.*;
import java.util.*;

public class IPLCensorAnalyzer {

    static class IPLMatch {
        public int match_id;
        public String team1;
        public String team2;
        public Map<String, Integer> score;
        public String winner;
        public String player_of_match;

        public IPLMatch() {}

        public IPLMatch(IPLMatch other) {
            this.match_id = other.match_id;
            this.team1 = censorTeamName(other.team1);
            this.team2 = censorTeamName(other.team2);

            this.score = new HashMap<>();
            for (Map.Entry<String, Integer> entry : other.score.entrySet()) {
                this.score.put(censorTeamName(entry.getKey()), entry.getValue());
            }

            this.winner = censorTeamName(other.winner);
            this.player_of_match = "REDACTED";
        }
    }

    public static void main(String[] args) throws Exception {
        String jsonInputFile = "ipl_matches.json";
        String csvInputFile = "ipl_matches.csv";

        String jsonOutputFile = "ipl_matches_censored.json";
        String csvOutputFile = "ipl_matches_censored.csv";

        List<IPLMatch> jsonMatches = readJson(jsonInputFile);
        List<IPLMatch> censoredJsonMatches = censorJsonMatches(jsonMatches);
        writeJson(censoredJsonMatches, jsonOutputFile);

        List<IPLMatch> csvMatches = readCsv(csvInputFile);
        List<IPLMatch> censoredCsvMatches = censorJsonMatches(csvMatches);
        writeCsv(censoredCsvMatches, csvOutputFile);
    }

    static String censorTeamName(String teamName) {
        if (teamName == null || teamName.isEmpty()) return teamName;
        String[] parts = teamName.split(" ");
        parts[parts.length - 1] = "***";
        return String.join(" ", parts);
    }

    static List<IPLMatch> readJson(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(filePath), new TypeReference<List<IPLMatch>>() {});
    }

    static void writeJson(List<IPLMatch> matches, String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), matches);
        System.out.println("Censored JSON written to " + filePath);
    }

    static List<IPLMatch> censorJsonMatches(List<IPLMatch> original) {
        List<IPLMatch> censored = new ArrayList<>();
        for (IPLMatch match : original) {
            censored.add(new IPLMatch(match));
        }
        return censored;
    }

    static List<IPLMatch> readCsv(String filePath) throws IOException {
        List<IPLMatch> matches = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            List<String[]> rows = reader.readAll();
            if (rows.isEmpty()) return matches;

            String[] headers = rows.get(0);
            for (int i = 1; i < rows.size(); i++) {
                String[] row = rows.get(i);
                IPLMatch match = new IPLMatch();
                for (int j = 0; j < headers.length; j++) {
                    switch (headers[j]) {
                        case "match_id" -> match.match_id = Integer.parseInt(row[j]);
                        case "team1" -> match.team1 = row[j];
                        case "team2" -> match.team2 = row[j];
                        case "score_team1" -> {
                            match.score = match.score == null ? new HashMap<>() : match.score;
                            match.score.put(match.team1, Integer.parseInt(row[j]));
                        }
                        case "score_team2" -> {
                            match.score = match.score == null ? new HashMap<>() : match.score;
                            match.score.put(match.team2, Integer.parseInt(row[j]));
                        }
                        case "winner" -> match.winner = row[j];
                        case "player_of_match" -> match.player_of_match = row[j];
                    }
                }
                matches.add(match);
            }
        }
        return matches;
    }

    static void writeCsv(List<IPLMatch> matches, String filePath) throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
          
            String[] header = {"match_id", "team1", "team2", "score_team1", "score_team2", "winner", "player_of_match"};
            writer.writeNext(header);

            for (IPLMatch match : matches) {
                String score1 = String.valueOf(match.score.getOrDefault(match.team1, 0));
                String score2 = String.valueOf(match.score.getOrDefault(match.team2, 0));

                String[] line = {
                        String.valueOf(match.match_id),
                        match.team1,
                        match.team2,
                        score1,
                        score2,
                        match.winner,
                        match.player_of_match
                };
                writer.writeNext(line);
            }
        }
        System.out.println("Censored CSV written to " + filePath);
    }
}

