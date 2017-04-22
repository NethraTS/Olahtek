package com.mongorest.olahtek.service;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import org.jongo.Jongo;
import org.jongo.MongoCursor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongorest.olahtek.model.AIMEIndexingFactor;
import com.mongorest.olahtek.model.FinPlan;
import com.mongorest.olahtek.model.IncomeProfile;
import com.mongorest.olahtek.model.Marriagegoalmodel;
import com.mongorest.olahtek.model.RetirementGoal;
import com.mongorest.olahtek.model.SSB;
import com.mongorest.olahtek.model.User;

@Service("ssbCalculator")
@Transactional
public class SSBCalculatorImpl implements SSBCalculator {
    private final MongoClient mongoClient = MongoDBConnection.mongoClient;
    private final MongoDatabase mongoDb = MongoDBConnection.mongoDb;
    private final Jongo jongo = MongoDBConnection.jongo;
    private final String mongoDBName = MongoDBConnection.mongoDBName;
    private final DB db = MongoDBConnection.db;
    private final JSONObject cm = MongoDBConnection.cm;
    private final String incomeProfileCollection = MongoDBConnection.incomeProfileCollection;
    private static final double FIRST_BENDPOINT = 856;
    private static final double SECOND_BENDPOINT = 5157;
    private static final double CONSTANT1 = 0.9;
    private static final double CONSTANT2 = 0.32;
    private static final double CONSTANT3 = 0.15;
    private static final double SSB_CONSTANT1 = 0.7;
    private static final double SSB_CONSTANT2 = 0.75;
    private static final double SSB_CONSTANT3 = 0.8;
    private static final double SSB_CONSTANT4 = 0.867;
    private static final double SSB_CONSTANT5 = 0.933;
    private static final double SSB_CONSTANT6 = 1.0;
    private static final double SSB_CONSTANT7 = 1.08;
    private static final double SSB_CONSTANT8 = 1.16;
    private static final double SSB_CONSTANT9 = 1.24;
    private static final double SSB_CONSTANT10 = 1.32;
    private static final double SPOUSE_CONSTANT62 = 0.325;
    private static final double SPOUSE_CONSTANT63 = 0.35;
    private static final double SPOUSE_CONSTANT64 = 0.375;
    private static final double SPOUSE_CONSTANT65 = 0.417;
    private static final double SPOUSE_CONSTANT66 = 0.455;
    private static final double SPOUSE_CONSTANT67 = 0.5;
    private static final int START_YEAR = 1943;
    private static final int END_YEAR = 1959;
    private static final int MONTHS = 12;
    private static final int MONTHS_YEARS = 420;
    private static final int YEARS = 35;

    private static final int RET_AGE1 = 62;
    private static final int RET_AGE2 = 63;
    private static final int RET_AGE3 = 64;
    private static final int RET_AGE4 = 65;
    private static final int RET_AGE5 = 66;
    private static final int RET_AGE6 = 67;
    private static final int RET_AGE7 = 68;
    private static final int RET_AGE8 = 69;
    private static final int RET_AGE9 = 70;

    private static final int INCOMEYEARLEN = 100;

    private final ObjectMapper jsonMapper = new ObjectMapper();
    private static DecimalFormat df2 = new DecimalFormat(".##");

    @Override
    public JSONObject calculateFinalSSB(final SSB ssb) {
        final JSONObject response = new JSONObject();
        df2.setRoundingMode(RoundingMode.UP);
        try {
            if (ssb.getAction().equals("calculateSSBSingle")) {
                final JSONObject response2 = displaySSBLess(ssb, "user");
                response.put("userSSB", response2);
                response.put("userFRA", response2.getInt("userFRA"));
                response.put("status", "success");
            } else if (ssb.getAction().equals("calculateSSBMarriedWorking")) {
                JSONObject response2 = new JSONObject();
                final JSONObject response3 = displaySSBLess(ssb, "user");
                response.put("userSSB", response3);
                response.put("userFRA", response3.getInt("userFRA"));
                response2 = displaySSBLess(ssb, "spouse");
                if (response2.getString("status").equals("halfOfUserSSB")) {
                    final double spouseSSBTemp = Double.parseDouble(response3.getString("userssb")) / 2;
                    final JSONObject tempObj = new JSONObject();
                    tempObj.put("spousessb", spouseSSBTemp);
                    tempObj.put("Spouse62", Double.parseDouble(response3.getString("User62")) / 2);
                    tempObj.put("Spouse63", Double.parseDouble(response3.getString("User63")) / 2);
                    tempObj.put("Spouse64", Double.parseDouble(response3.getString("User64")) / 2);
                    tempObj.put("Spouse65", Double.parseDouble(response3.getString("User65")) / 2);
                    tempObj.put("Spouse66", Double.parseDouble(response3.getString("User66")) / 2);
                    tempObj.put("Spouse67", Double.parseDouble(response3.getString("User67")) / 2);
                    tempObj.put("Spouse68", Double.parseDouble(response3.getString("User68")) / 2);
                    tempObj.put("Spouse69", Double.parseDouble(response3.getString("User69")) / 2);
                    tempObj.put("Spouse70", Double.parseDouble(response3.getString("User70")) / 2);
                    response.put("spouseSSB", tempObj);
                } else {
                    final JSONObject responseSpouse = checkSpouseSSB(response2, response3,
                            Integer.parseInt(ssb.getUserRetirementAge()),
                            Integer.parseInt(ssb.getSpouseRetirementAge()));
                    response.put("spouseSSB", responseSpouse);
                    response.put("spouseFRA", responseSpouse.getInt("spouseFRA"));
                    response.put("earlySpouseRetAge", RET_AGE1);
                }
                response.put("status", "success");
            } else if (ssb.getAction().equals("calculateSSBMarriedNotWorking")) {
                final JSONObject response3 = displaySSBLess(ssb, "user");
                response.put("userSSB", response3);
                response.put("userFRA", response3.getInt("userFRA"));
                final double spouseSSBTemp = Double.parseDouble(response3.getString("userssb")) / 2;
                final JSONObject tempObj = new JSONObject();
                tempObj.put("spousessb", spouseSSBTemp);
                tempObj.put("Spouse62", Double.parseDouble(response3.getString("User62")) / 2);
                tempObj.put("Spouse63", Double.parseDouble(response3.getString("User63")) / 2);
                tempObj.put("Spouse64", Double.parseDouble(response3.getString("User64")) / 2);
                tempObj.put("Spouse65", Double.parseDouble(response3.getString("User65")) / 2);
                tempObj.put("Spouse66", Double.parseDouble(response3.getString("User66")) / 2);
                tempObj.put("Spouse67", Double.parseDouble(response3.getString("User67")) / 2);
                tempObj.put("Spouse68", Double.parseDouble(response3.getString("User68")) / 2);
                tempObj.put("Spouse69", Double.parseDouble(response3.getString("User69")) / 2);
                tempObj.put("Spouse70", Double.parseDouble(response3.getString("User70")) / 2);
                response.put("spouseSSB", tempObj);
                response.put("status", "success");
            }
            return response;
        } catch (final Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public JSONObject checkSpouseSSB(final JSONObject spouseSSBCheck, final JSONObject userSSBCheck,
            final int userRetAge, final int spouseRetAge) {
        try {
            final JSONObject spouseSSB = spouseSSBCheck;
            final String s1 = "User" + userRetAge;
            final String s2 = "Spouse" + spouseRetAge;
            if (Double.parseDouble(spouseSSB.getString(s2)) < (Double.parseDouble(userSSBCheck.getString(s1)) / 2)) {
                spouseSSB.put(s2, df2.format(Double.parseDouble(userSSBCheck.getString(s1)) / 2));
            }
            return spouseSSB;
        } catch (final Exception e) {
            System.out.println("Exception::" + e);
            return null;
        }
    }

    public JSONObject displaySSBLess(final SSB ssb, final String user) {
        try {
            final JSONObject userSSB = new JSONObject();
            if (user.equals("user")) {
                final Date date = new Date();
                final Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                final int year = cal.get(Calendar.YEAR);
                for (int retAge = RET_AGE1; retAge <= RET_AGE9; retAge++) {
                    final int avgUserAge = retAge - Integer.parseInt(ssb.getUser_age());
                    if (avgUserAge >= YEARS) {
                        double userAime = 0;
                        if (ssb.getUserAime().equals("Yes")) {
                            userAime = Long.parseLong(ssb.getUserAnnaul_income());
                        } else {
                            final double avgUserIncome = YEARS * (Long.parseLong(ssb.getUserAnnaul_income()) * MONTHS);
                            userAime = avgUserIncome / MONTHS_YEARS;
                        }
                        int userFra = 0;
                        if (year - Integer.parseInt(ssb.getUser_age()) >= START_YEAR
                                && year - Integer.parseInt(ssb.getUser_age()) <= END_YEAR) {
                            userFra = RET_AGE5;
                        } else {
                            userFra = RET_AGE6;
                        }
                        double userssb = 0;
                        if (userAime <= FIRST_BENDPOINT) {
                            userssb = userAime * CONSTANT1;
                        } else if (userAime > FIRST_BENDPOINT && userAime <= SECOND_BENDPOINT) {
                            userssb = (FIRST_BENDPOINT * CONSTANT1) + (userAime - FIRST_BENDPOINT) * CONSTANT2;
                        } else {
                            userssb = (FIRST_BENDPOINT * CONSTANT1) + ((SECOND_BENDPOINT - FIRST_BENDPOINT) * CONSTANT2)
                                    + ((userAime - SECOND_BENDPOINT) * CONSTANT3);
                        }
                        userSSB.put("userssb", userssb * MONTHS);
                        userSSB.put("userFRA", userFra);
                        if (userFra == RET_AGE5) {
                            if (retAge == RET_AGE1) {
                                userSSB.put("User62", df2.format(userssb * SSB_CONSTANT2 * MONTHS));
                            } else if (retAge == RET_AGE2) {
                                userSSB.put("User63", df2.format(userssb * SSB_CONSTANT3 * MONTHS));
                            } else if (retAge == RET_AGE3) {
                                userSSB.put("User64", df2.format(userssb * SSB_CONSTANT4 * MONTHS));
                            } else if (retAge == RET_AGE4) {
                                userSSB.put("User65", df2.format(userssb * SSB_CONSTANT5 * MONTHS));
                            } else if (retAge == RET_AGE5) {
                                userSSB.put("User66", df2.format(userssb * SSB_CONSTANT6 * MONTHS));
                            } else if (retAge == RET_AGE6) {
                                userSSB.put("User67", df2.format(userssb * SSB_CONSTANT7 * MONTHS));
                            } else if (retAge == RET_AGE7) {
                                userSSB.put("User68", df2.format(userssb * SSB_CONSTANT8 * MONTHS));
                            } else if (retAge == RET_AGE8) {
                                userSSB.put("User69", df2.format(userssb * SSB_CONSTANT9 * MONTHS));
                            } else if (retAge == RET_AGE9) {
                                userSSB.put("User70", df2.format(userssb * SSB_CONSTANT10 * MONTHS));
                            }
                        } else {
                            if (retAge == RET_AGE1) {
                                userSSB.put("User62", df2.format(userssb * SSB_CONSTANT1 * MONTHS));
                            } else if (retAge == RET_AGE2) {
                                userSSB.put("User63", df2.format(userssb * SSB_CONSTANT2 * MONTHS));
                            } else if (retAge == RET_AGE3) {
                                userSSB.put("User64", df2.format(userssb * SSB_CONSTANT3 * MONTHS));
                            } else if (retAge == RET_AGE4) {
                                userSSB.put("User65", df2.format(userssb * SSB_CONSTANT4 * MONTHS));
                            } else if (retAge == RET_AGE5) {
                                userSSB.put("User66", df2.format(userssb * SSB_CONSTANT5 * MONTHS));
                            } else if (retAge == RET_AGE6) {
                                userSSB.put("User67", df2.format(userssb * SSB_CONSTANT6 * MONTHS));
                            } else if (retAge == RET_AGE7) {
                                userSSB.put("User68", df2.format(userssb * SSB_CONSTANT7 * MONTHS));
                            } else if (retAge == RET_AGE8) {
                                userSSB.put("User69", df2.format(userssb * SSB_CONSTANT8 * MONTHS));
                            } else if (retAge == RET_AGE9) {
                                userSSB.put("User70", df2.format(userssb * SSB_CONSTANT9 * MONTHS));
                            }
                        }
                    } else {
                        double userAime = 0;
                        if (ssb.getUserAime().equals("Yes")) {
                            userAime = Long.parseLong(ssb.getUserAnnaul_income());
                        } else {
                            final double avgUserIncome = avgUserAge
                                    * (Long.parseLong(ssb.getUserAnnaul_income()) * MONTHS);
                            userAime = avgUserIncome / MONTHS_YEARS;
                        }

                        int userFra = 0;
                        if (year - Integer.parseInt(ssb.getUser_age()) >= START_YEAR
                                && year - Integer.parseInt(ssb.getUser_age()) <= END_YEAR) {
                            userFra = RET_AGE5;
                        } else {
                            userFra = RET_AGE6;
                        }
                        userSSB.put("userFRA", userFra);
                        double userssb = 0;

                        if (userAime <= FIRST_BENDPOINT) {
                            userssb = userAime * CONSTANT1;
                        } else if (userAime > FIRST_BENDPOINT && userAime <= SECOND_BENDPOINT) {
                            userssb = (FIRST_BENDPOINT * CONSTANT1) + (userAime - FIRST_BENDPOINT) * CONSTANT2;
                        } else {
                            userssb = (FIRST_BENDPOINT * CONSTANT1) + ((SECOND_BENDPOINT - FIRST_BENDPOINT) * CONSTANT2)
                                    + ((userAime - SECOND_BENDPOINT) * CONSTANT3);
                        }

                        if (retAge == Integer.parseInt(ssb.getUserRetirementAge())) {
                            userSSB.put("userssb", df2.format(userssb * MONTHS));
                        }

                        if (userFra == RET_AGE5) {
                            if (retAge == RET_AGE1) {
                                userSSB.put("User62", df2.format(userssb * SSB_CONSTANT2 * MONTHS));
                            } else if (retAge == RET_AGE2) {
                                userSSB.put("User63", df2.format(userssb * SSB_CONSTANT3 * MONTHS));
                            } else if (retAge == RET_AGE3) {
                                userSSB.put("User64", df2.format(userssb * SSB_CONSTANT4 * MONTHS));
                            } else if (retAge == RET_AGE4) {
                                userSSB.put("User65", df2.format(userssb * SSB_CONSTANT5 * MONTHS));
                            } else if (retAge == RET_AGE5) {
                                userSSB.put("User66", df2.format(userssb * SSB_CONSTANT6 * MONTHS));
                            } else if (retAge == RET_AGE6) {
                                userSSB.put("User67", df2.format(userssb * SSB_CONSTANT7 * MONTHS));
                            } else if (retAge == RET_AGE7) {
                                userSSB.put("User68", df2.format(userssb * SSB_CONSTANT8 * MONTHS));
                            } else if (retAge == RET_AGE8) {
                                userSSB.put("User69", df2.format(userssb * SSB_CONSTANT9 * MONTHS));
                            } else if (retAge == RET_AGE9) {
                                userSSB.put("User70", df2.format(userssb * SSB_CONSTANT10 * MONTHS));
                            }
                        } else {
                            if (retAge == RET_AGE1) {
                                userSSB.put("User62", df2.format(userssb * SSB_CONSTANT1 * MONTHS));
                            } else if (retAge == RET_AGE2) {
                                userSSB.put("User63", df2.format(userssb * SSB_CONSTANT2 * MONTHS));
                            } else if (retAge == RET_AGE3) {
                                userSSB.put("User64", df2.format(userssb * SSB_CONSTANT3 * MONTHS));
                            } else if (retAge == RET_AGE4) {
                                userSSB.put("User65", df2.format(userssb * SSB_CONSTANT4 * MONTHS));
                            } else if (retAge == RET_AGE5) {
                                userSSB.put("User66", df2.format(userssb * SSB_CONSTANT5 * MONTHS));
                            } else if (retAge == RET_AGE6) {
                                userSSB.put("User67", df2.format(userssb * SSB_CONSTANT6 * MONTHS));
                            } else if (retAge == RET_AGE7) {
                                userSSB.put("User68", df2.format(userssb * SSB_CONSTANT7 * MONTHS));
                            } else if (retAge == RET_AGE8) {
                                userSSB.put("User69", df2.format(userssb * SSB_CONSTANT8 * MONTHS));
                            } else if (retAge == RET_AGE9) {
                                userSSB.put("User70", df2.format(userssb * SSB_CONSTANT9 * MONTHS));
                            }
                        }
                    }
                }
            } else if (user.equals("spouse")) {
                userSSB.put("status", "");
                if (Long.parseLong(ssb.getSpouseAnnaul_income()) > 0) {
                    final Date date = new Date(); // your date
                    final Calendar cal = Calendar.getInstance();
                    cal.setTime(date);
                    final int year = cal.get(Calendar.YEAR);
                    for (int sretAge = RET_AGE1; sretAge <= RET_AGE9; sretAge++) {
                        final int avgSpouseAge = sretAge - Integer.parseInt(ssb.getSpouse_age());
                        if (avgSpouseAge >= YEARS) {
                            double spouseAime = 0;
                            if (ssb.getSpouseAime().equals("Yes")) {
                                spouseAime = Long.parseLong(ssb.getSpouseAnnaul_income());
                            } else {
                                final double avgSpouseIncome = YEARS
                                        * (Long.parseLong(ssb.getSpouseAnnaul_income()) * MONTHS);
                                spouseAime = avgSpouseIncome / MONTHS_YEARS;
                            }

                            int spouseFra = 0;
                            if (year - Integer.parseInt(ssb.getSpouse_age()) >= START_YEAR
                                    && year - Integer.parseInt(ssb.getSpouse_age()) <= END_YEAR) {
                                spouseFra = RET_AGE5;
                            } else {
                                spouseFra = RET_AGE6;
                            }
                            userSSB.put("spouseFRA", spouseFra);
                            double spousessb = 0;
                            if (spouseAime <= FIRST_BENDPOINT) {
                                spousessb = spouseAime * CONSTANT1;
                            } else if (spouseAime > FIRST_BENDPOINT && spouseAime <= SECOND_BENDPOINT) {
                                spousessb = (FIRST_BENDPOINT * CONSTANT1) + (spouseAime - FIRST_BENDPOINT) * CONSTANT2;
                            } else {
                                spousessb = (FIRST_BENDPOINT * CONSTANT1)
                                        + ((SECOND_BENDPOINT - FIRST_BENDPOINT) * CONSTANT2)
                                        + ((spouseAime - SECOND_BENDPOINT) * CONSTANT3);
                            }
                            if (spousessb < 0) {
                                spousessb = 0;
                            }

                            if (spousessb != 0) {
                                userSSB.put("spousessb", df2.format(spousessb * MONTHS));
                                if (sretAge == RET_AGE1) {
                                    userSSB.put("Spouse62", df2.format(spousessb * SSB_CONSTANT1 * MONTHS));
                                } else if (sretAge == RET_AGE2) {
                                    userSSB.put("Spouse63", df2.format(spousessb * SSB_CONSTANT2 * MONTHS));
                                } else if (sretAge == RET_AGE3) {
                                    userSSB.put("Spouse64", df2.format(spousessb * SSB_CONSTANT3 * MONTHS));
                                } else if (sretAge == RET_AGE4) {
                                    userSSB.put("Spouse65", df2.format(spousessb * SSB_CONSTANT4 * MONTHS));
                                } else if (sretAge == RET_AGE5) {
                                    userSSB.put("Spouse66", df2.format(spousessb * SSB_CONSTANT5 * MONTHS));
                                } else if (sretAge == RET_AGE6) {
                                    userSSB.put("Spouse67", df2.format(spousessb * SSB_CONSTANT6 * MONTHS));
                                } else if (sretAge == RET_AGE7) {
                                    userSSB.put("Spouse68", df2.format(spousessb * SSB_CONSTANT7 * MONTHS));
                                } else if (sretAge == RET_AGE8) {
                                    userSSB.put("Spouse69", df2.format(spousessb * SSB_CONSTANT8 * MONTHS));
                                } else if (sretAge == RET_AGE9) {
                                    userSSB.put("Spouse70", df2.format(spousessb * SSB_CONSTANT9 * MONTHS));
                                }
                            } else {
                                userSSB.put("spousessb", 0);
                                if (sretAge == RET_AGE1) {
                                    userSSB.put("Spouse62", df2.format(spousessb * SPOUSE_CONSTANT62 * MONTHS));
                                } else if (sretAge == RET_AGE2) {
                                    userSSB.put("Spouse63", df2.format(spousessb * SPOUSE_CONSTANT63 * MONTHS));
                                } else if (sretAge == RET_AGE3) {
                                    userSSB.put("Spouse64", df2.format(spousessb * SPOUSE_CONSTANT64 * MONTHS));
                                } else if (sretAge == RET_AGE4) {
                                    userSSB.put("Spouse65", df2.format(spousessb * SPOUSE_CONSTANT65 * MONTHS));
                                } else if (sretAge == RET_AGE5) {
                                    userSSB.put("Spouse66", df2.format(spousessb * SPOUSE_CONSTANT66 * MONTHS));
                                } else if (sretAge == RET_AGE6) {
                                    userSSB.put("Spouse67", df2.format(spousessb * SPOUSE_CONSTANT67 * MONTHS));
                                } else if (sretAge == RET_AGE7) {
                                    userSSB.put("Spouse68", df2.format(spousessb * SPOUSE_CONSTANT67 * MONTHS));
                                } else if (sretAge == RET_AGE8) {
                                    userSSB.put("Spouse69", df2.format(spousessb * SPOUSE_CONSTANT67 * MONTHS));
                                } else if (sretAge == RET_AGE9) {
                                    userSSB.put("Spouse70", df2.format(spousessb * SPOUSE_CONSTANT67 * MONTHS));
                                }
                            }
                        } else {
                            double spouseAime = 0;
                            if (ssb.getSpouseAime().equals("Yes")) {
                                spouseAime = Long.parseLong(ssb.getSpouseAnnaul_income());
                            } else {
                                final double avgSpouseIncome = avgSpouseAge
                                        * (Long.parseLong(ssb.getSpouseAnnaul_income()) * MONTHS);
                                spouseAime = avgSpouseIncome / MONTHS_YEARS;
                            }

                            int spouseFra = 0;
                            if (year - Integer.parseInt(ssb.getSpouse_age()) >= START_YEAR
                                    && year - Integer.parseInt(ssb.getSpouse_age()) <= END_YEAR) {
                                spouseFra = RET_AGE5;
                            } else {
                                spouseFra = RET_AGE6;
                            }
                            userSSB.put("spouseFRA", spouseFra);
                            double spousessb = 0;
                            if (spouseAime <= FIRST_BENDPOINT) {
                                spousessb = spouseAime * CONSTANT1;
                            } else if (spouseAime > FIRST_BENDPOINT && spouseAime <= SECOND_BENDPOINT) {
                                spousessb = (FIRST_BENDPOINT * CONSTANT1) + (spouseAime - FIRST_BENDPOINT) * CONSTANT2;
                            } else {
                                spousessb = (FIRST_BENDPOINT * CONSTANT1)
                                        + ((SECOND_BENDPOINT - FIRST_BENDPOINT) * CONSTANT2)
                                        + ((spouseAime - SECOND_BENDPOINT) * CONSTANT3);
                            }

                            if (sretAge == Integer.parseInt(ssb.getSpouseRetirementAge())) {
                                userSSB.put("spousessb", df2.format(spousessb * MONTHS));
                            }
                            if (spousessb != 0) {
                                if (sretAge == RET_AGE1) {
                                    userSSB.put("Spouse62", df2.format(spousessb * SSB_CONSTANT1 * MONTHS));
                                } else if (sretAge == RET_AGE2) {
                                    userSSB.put("Spouse63", df2.format(spousessb * SSB_CONSTANT2 * MONTHS));
                                } else if (sretAge == RET_AGE3) {
                                    userSSB.put("Spouse64", df2.format(spousessb * SSB_CONSTANT3 * MONTHS));
                                } else if (sretAge == RET_AGE4) {
                                    userSSB.put("Spouse65", df2.format(spousessb * SSB_CONSTANT4 * MONTHS));
                                } else if (sretAge == RET_AGE5) {
                                    userSSB.put("Spouse66", df2.format(spousessb * SSB_CONSTANT5 * MONTHS));
                                } else if (sretAge == RET_AGE6) {
                                    userSSB.put("Spouse67", df2.format(spousessb * SSB_CONSTANT6 * MONTHS));
                                } else if (sretAge == RET_AGE7) {
                                    userSSB.put("Spouse68", df2.format(spousessb * SSB_CONSTANT7 * MONTHS));
                                } else if (sretAge == RET_AGE8) {
                                    userSSB.put("Spouse69", df2.format(spousessb * SSB_CONSTANT8 * MONTHS));
                                } else if (sretAge == RET_AGE9) {
                                    userSSB.put("Spouse70", df2.format(spousessb * SSB_CONSTANT9 * MONTHS));
                                }
                            } else {
                                if (sretAge == RET_AGE1) {
                                    userSSB.put("Spouse62", df2.format(spousessb * SPOUSE_CONSTANT62 * MONTHS));
                                } else if (sretAge == RET_AGE2) {
                                    userSSB.put("Spouse63", df2.format(spousessb * SPOUSE_CONSTANT63 * MONTHS));
                                } else if (sretAge == RET_AGE3) {
                                    userSSB.put("Spouse64", df2.format(spousessb * SPOUSE_CONSTANT64 * MONTHS));
                                } else if (sretAge == RET_AGE4) {
                                    userSSB.put("Spouse65", df2.format(spousessb * SPOUSE_CONSTANT65 * MONTHS));
                                } else if (sretAge == RET_AGE5) {
                                    userSSB.put("Spouse66", df2.format(spousessb * SPOUSE_CONSTANT66 * MONTHS));
                                } else if (sretAge == RET_AGE6) {
                                    userSSB.put("Spouse67", df2.format(spousessb * SPOUSE_CONSTANT67 * MONTHS));
                                } else if (sretAge == RET_AGE7) {
                                    userSSB.put("Spouse68", df2.format(spousessb * SPOUSE_CONSTANT67 * MONTHS));
                                } else if (sretAge == RET_AGE8) {
                                    userSSB.put("Spouse69", df2.format(spousessb * SPOUSE_CONSTANT67 * MONTHS));
                                } else if (sretAge == RET_AGE9) {
                                    userSSB.put("Spouse70", df2.format(spousessb * SPOUSE_CONSTANT67 * MONTHS));
                                }
                            }
                        }
                    }
                } else {
                    userSSB.put("status", "halfOfUserSSB");
                }
            }
            return userSSB;
        } catch (final Exception e) {
            System.out.println("Exception::" + e);
            return null;
        }
    }

    @Override
    public JSONObject fetchIncomeProfile(final SSB ssb) {
        try {
            final Date date = new Date(); // your date
            final Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            final int year = cal.get(Calendar.YEAR);

            final JSONObject jobject = new JSONObject();
            final User details = MongoDBConnection.userColl.findOne("{_id:#}", ssb.getUser_id()).as(User.class);
            jobject.put("userName", details.getName());
            jobject.put("userBirthYear", details.getBirthYear());
            jobject.put("userMaritalStatus", details.getMarital_status());
            jobject.put("userAge", details.getAge());
            jobject.put("spouseBirthYear", "");
            jobject.put("spouseAge", "");
            if (details.getMarital_status().equalsIgnoreCase("Yes")) {
                jobject.put("spouseBirthYear", details.getSpouseBirthYear());
                jobject.put("spouseAge", details.getSpouseAge());
            }

            int i = 0;
            final MongoCursor<FinPlan> cursor = MongoDBConnection.finplancol.find("{usr_id:#}", ssb.getUser_id())
                    .as(FinPlan.class);
            final JSONArray incomeProfiles = new JSONArray();
            final JSONArray incomeProfilesObjects = new JSONArray();
            if (cursor.count() > 0) {
                final JSONArray pln = new JSONArray();
                while (cursor.hasNext()) {
                    i = i + 1;
                    final FinPlan fetch = cursor.next();
                    pln.put(fetch.getPlan_name());
                    incomeProfiles.put(fetch.getProfile_name());
                    String[] goals;
                    goals = fetch.getGoals();
                    final Marriagegoalmodel marriage = MongoDBConnection.goalcoll
                            .findOne("{user_id:#,goalType:#}", ssb.getUser_id(), "Marriage")
                            .as(Marriagegoalmodel.class);
                    if (marriage != null) {
                        jobject.put("goalMarried", "Yes");
                        jobject.put("spouseBirthYear", marriage.getBirthYear());
                        jobject.put("spouseAge", marriage.getSpouseAge());
                    }

                    if (goals.length > 0) {
                        for (final String elem : goals) {
                            final RetirementGoal goal = MongoDBConnection.goalcoll
                                    .findOne("{_id:#,goalType:#}", elem, "Retirement").as(RetirementGoal.class);
                            if (goal != null) {
                                jobject.put("userRetAge", goal.getRetirementAge());
                                jobject.put("spouseRetAge", goal.getUserSelectedSpouseRetirementAge());
                            } else {
                                if (year - details.getBirthYear() >= START_YEAR
                                        && year - details.getBirthYear() <= END_YEAR) {
                                    jobject.put("userRetAge", "66");
                                } else {
                                    jobject.put("userRetAge", "67");
                                }
                                if (marriage != null || details.getMarital_status().equalsIgnoreCase("Yes")) {
                                    if (year - details.getSpouseBirthYear() >= START_YEAR
                                            && year - details.getSpouseBirthYear() <= END_YEAR) {
                                        jobject.put("spouseRetAge", "66");
                                    } else {
                                        jobject.put("spouseRetAge", "67");
                                    }
                                }
                            }
                        }
                    } else {
                        if (year - details.getBirthYear() >= START_YEAR && year - details.getBirthYear() <= END_YEAR) {
                            jobject.put("userRetAge", "66");
                        } else {
                            jobject.put("userRetAge", "67");
                        }
                        if (details.getMarital_status().equalsIgnoreCase("Yes")) {
                            if (year - details.getSpouseBirthYear() >= START_YEAR
                                    && year - details.getSpouseBirthYear() <= END_YEAR) {
                                jobject.put("spouseRetAge", "66");
                            } else {
                                jobject.put("spouseRetAge", "67");
                            }
                        }

                    }
                }
                cursor.close();
                jobject.put("Plans", pln);
            } else {
                if (year - details.getBirthYear() >= START_YEAR && year - details.getBirthYear() <= END_YEAR) {
                    jobject.put("userRetAge", "66");
                } else {
                    jobject.put("userRetAge", "67");
                }
                if (details.getMarital_status().equalsIgnoreCase("Yes")) {
                    if (year - details.getSpouseBirthYear() >= START_YEAR
                            && year - details.getSpouseBirthYear() <= END_YEAR) {
                        jobject.put("spouseRetAge", "66");
                    } else {
                        jobject.put("spouseRetAge", "67");
                    }
                }
            }
            final MongoCursor<IncomeProfile> cursorIncome = MongoDBConnection.incomeProfileCol
                    .find("{user_id:#}", ssb.getUser_id()).as(IncomeProfile.class);

            if (cursorIncome.count() > 0) {
                while (cursorIncome.hasNext()) {
                    i = i + 1;
                    final IncomeProfile fetch = cursorIncome.next();
                    incomeProfiles.put(fetch.getProfile_name());
                    final JSONObject tempObj = new JSONObject();
                    tempObj.put("prof_name", fetch.getProfile_name());
                    final JSONArray spouseIncome = new JSONArray(fetch.getSpouse_income());
                    tempObj.put("spouseIncomeLength", spouseIncome.length());
                    incomeProfilesObjects.put(tempObj);
                }
            } else {
                incomeProfiles.put("constant_income");
            }

            jobject.put("incomeProfile", incomeProfiles);
            jobject.put("incomeProfileObjects", incomeProfilesObjects);
            jobject.put("status", "success");
            return jobject;
        } catch (final Exception e) {
            System.out.println("Exception::" + e);
            return null;
        }

    }

    public double findUserAIME(final int retirementAge, final JSONArray incomeJsonArray, final int endYear) {

        double userAime = 0;
        try {
            int numberOfWorkingYear = 0;
            final DecimalFormat decimalFloat = new DecimalFormat("#.##");
            final double[] incomeArray = new double[INCOMEYEARLEN];
            final int incomeStartYear = incomeJsonArray.getJSONObject(0).getInt("year");
            numberOfWorkingYear = endYear - incomeStartYear;
            System.out.println("numberOfWorkingYear=========" + numberOfWorkingYear);
            try {
                System.out.print("inside user AIME===========one");
                for (int i = 0; i < numberOfWorkingYear; i++) {
                    final int incomeYear = incomeJsonArray.getJSONObject(i).getInt("year");
                    final double userIncome = incomeJsonArray.getJSONObject(i).getInt("value");
                    final AIMEIndexingFactor indexFactor = MongoDBConnection.indexFactor
                            .findOne("{'year':#}", incomeYear).as(AIMEIndexingFactor.class);
                    if (indexFactor == null) {
                        incomeArray[i] = userIncome * 1;
                    } else {
                        incomeArray[i] = userIncome * indexFactor.getIndexingFactor();
                    }
                }
            } catch (final Exception e) {
                e.printStackTrace();
            }
            Arrays.sort(incomeArray);
            final int arrayLength = incomeArray.length;
            int j = 1;

            for (int i = arrayLength - 1; i >= 0; i--) {
                System.out.print(incomeArray[i] + "   ");
                userAime = userAime + incomeArray[i];
                if (j == numberOfWorkingYear || j == YEARS) {
                    break;
                }
                j++;
            }
            userAime = Double.parseDouble(decimalFloat.format(userAime / MONTHS_YEARS));
            System.out.println("userAime==============" + userAime);
            if (userAime < 0) {
                userAime = 0;
            }
            return userAime;
        } catch (final Exception e) {
            e.printStackTrace();
            return userAime;
        }
    }

    public JSONObject calculateUserSSB(final double spouseIncome, final String marritalStatus, final int useFfra,
            final int spouseFfra, final double firstBendPoint, final double secondBendPoint, final double userAime,
            final double spouseAime) {

        int spouseFra = spouseFfra;
        if (spouseFra == 0) {
            spouseFra = useFfra;
        }
        final JSONObject jsonSSB = new JSONObject();
        double userSsb = 0;
        double spouseSsb = 0;
        try {
            userSsb = calculateSSB(firstBendPoint, secondBendPoint, userAime);
            if (userSsb < 0) {
                userSsb = 0;
            }
            jsonSSB.put("userssb", df2.format(userSsb));
            if (useFfra == RET_AGE1) {
                spouseSsb = userSsb;
                jsonSSB.put("User62", df2.format(userSsb * SSB_CONSTANT2 * MONTHS));
                jsonSSB.put("User63", df2.format(userSsb * SSB_CONSTANT3 * MONTHS));
                jsonSSB.put("User64", df2.format(userSsb * SSB_CONSTANT4 * MONTHS));
                jsonSSB.put("User65", df2.format(userSsb * SSB_CONSTANT5 * MONTHS));
                jsonSSB.put("User66", df2.format(userSsb * SSB_CONSTANT6 * MONTHS));
                jsonSSB.put("User67", df2.format(userSsb * SSB_CONSTANT7 * MONTHS));
                jsonSSB.put("User68", df2.format(userSsb * SSB_CONSTANT8 * MONTHS));
                jsonSSB.put("User69", df2.format(userSsb * SSB_CONSTANT9 * MONTHS));
                jsonSSB.put("User70", df2.format(userSsb * SSB_CONSTANT10 * MONTHS));

                if (marritalStatus.equals("Yes")) {

                    if (spouseIncome == 0) {

                        spouseSsb = userSsb;
                        jsonSSB.put("spousessb", df2.format(spouseSsb));
                        jsonSSB.put("Spouse62", df2.format(spouseSsb * SPOUSE_CONSTANT62 * MONTHS));
                        jsonSSB.put("Spouse63", df2.format(spouseSsb * SPOUSE_CONSTANT63 * MONTHS));
                        jsonSSB.put("Spouse64", df2.format(spouseSsb * SPOUSE_CONSTANT64 * MONTHS));
                        jsonSSB.put("Spouse65", df2.format(spouseSsb * SPOUSE_CONSTANT65 * MONTHS));
                        jsonSSB.put("Spouse66", df2.format(spouseSsb * SPOUSE_CONSTANT66 * MONTHS));
                        jsonSSB.put("Spouse67", df2.format(spouseSsb * SPOUSE_CONSTANT67 * MONTHS));
                        jsonSSB.put("Spouse68", df2.format(spouseSsb * SPOUSE_CONSTANT67 * MONTHS));
                        jsonSSB.put("Spouse69", df2.format(spouseSsb * SPOUSE_CONSTANT67 * MONTHS));
                        jsonSSB.put("Spouse70", df2.format(spouseSsb * SPOUSE_CONSTANT67 * MONTHS));
                    }
                }

            } else if (useFfra == RET_AGE6) {
                spouseSsb = userSsb;
                jsonSSB.put("User62", df2.format(userSsb * SSB_CONSTANT1 * MONTHS));
                jsonSSB.put("User63", df2.format(userSsb * SSB_CONSTANT2 * MONTHS));
                jsonSSB.put("User64", df2.format(userSsb * SSB_CONSTANT3 * MONTHS));
                jsonSSB.put("User65", df2.format(userSsb * SSB_CONSTANT4 * MONTHS));
                jsonSSB.put("User66", df2.format(userSsb * SSB_CONSTANT5 * MONTHS));
                jsonSSB.put("User67", df2.format(userSsb * SSB_CONSTANT6 * MONTHS));
                jsonSSB.put("User68", df2.format(userSsb * SSB_CONSTANT7 * MONTHS));
                jsonSSB.put("User69", df2.format(userSsb * SSB_CONSTANT8 * MONTHS));
                jsonSSB.put("User70", df2.format(userSsb * SSB_CONSTANT9 * MONTHS));
                if (marritalStatus.equals("Yes")) {

                    if (spouseIncome == 0) {
                        spouseSsb = userSsb;
                        jsonSSB.put("spousessb", df2.format(spouseSsb));
                        jsonSSB.put("Spouse62", df2.format(spouseSsb * SPOUSE_CONSTANT62 * MONTHS));
                        jsonSSB.put("Spouse63", df2.format(spouseSsb * SPOUSE_CONSTANT63 * MONTHS));
                        jsonSSB.put("Spouse64", df2.format(spouseSsb * SPOUSE_CONSTANT64 * MONTHS));
                        jsonSSB.put("Spouse65", df2.format(spouseSsb * SPOUSE_CONSTANT65 * MONTHS));
                        jsonSSB.put("Spouse66", df2.format(spouseSsb * SPOUSE_CONSTANT66 * MONTHS));
                        jsonSSB.put("Spouse67", df2.format(spouseSsb * SPOUSE_CONSTANT67 * MONTHS));
                        jsonSSB.put("Spouse68", df2.format(spouseSsb * SPOUSE_CONSTANT67 * MONTHS));
                        jsonSSB.put("Spouse69", df2.format(spouseSsb * SPOUSE_CONSTANT67 * MONTHS));
                        jsonSSB.put("Spouse70", df2.format(spouseSsb * SPOUSE_CONSTANT67 * MONTHS));
                    }
                }
            }
            return jsonSSB;
        } catch (final Exception e) {
            e.printStackTrace();
            return jsonSSB;
        }
    }

    public double calculateSSB(final double firstBendPoint, final double secondBendPoint, final double aime) {
        double ssb = 0;
        try {
            if (aime <= firstBendPoint) {
                ssb = aime * CONSTANT1;
            } else if (aime > firstBendPoint && aime <= secondBendPoint) {
                ssb = firstBendPoint * CONSTANT1 + (aime - firstBendPoint) * CONSTANT2;
            } else {
                ssb = firstBendPoint * CONSTANT1 + (secondBendPoint - firstBendPoint) * CONSTANT2
                        + (aime - secondBendPoint) * CONSTANT3;
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return ssb;
    }

    @Override
    public JSONObject calculateSSBFromProfile(final SSB ssb) {
        try {
            final JSONObject jobject = new JSONObject();
            IncomeProfile incomeProf = null;
            final User user = MongoDBConnection.userColl.findOne("{_id:#}", ssb.getUser_id()).as(User.class);
            final String maritalStatusDB = user.getMarital_status();
            if (maritalStatusDB.equals("Yes")) {
            	final RetirementGoal goal = MongoDBConnection.goalcoll
                        .findOne("{user_id:#,goalType:#}", ssb.getUser_id(), "Retirement").as(RetirementGoal.class);
                System.out.println("goal :: "+goal);
                if (goal != null) {
                	jobject.put("earlySpouseRetAge",goal.getUserSelectedSpouseRetirementAge());
                }
                else {
                	jobject.put("earlySpouseRetAge", RET_AGE1);
                }
            } else {
            	jobject.put("earlySpouseRetAge", RET_AGE1);
            }
            incomeProf = MongoDBConnection.incomeProfileCol
                    .findOne("{user_id:#,profile_name:#}", ssb.getUser_id(), ssb.getProf_name())
                    .as(IncomeProfile.class);
            final JSONObject incomeJson = new JSONObject(jsonMapper.writeValueAsString(incomeProf));
            JSONArray userIncomeArray = new JSONArray();
            JSONArray combinedArray = new JSONArray();
            JSONArray spouseIncomeAraay = new JSONArray();
            int spouseStartYear = 0;
            final int spouseAge = 0;
            final int spouseRetirementAge = 0;
            final double spouseIncome = 0;
            userIncomeArray = incomeJson.getJSONArray("user_income");
            final int userStartYear = Integer.parseInt(ssb.getBirth_year())
                    + Integer.parseInt(ssb.getUserRetirementAge());
            final double userAime = findUserAIME(Integer.parseInt(ssb.getUserRetirementAge()), userIncomeArray,
                    userStartYear);
            double spouseAime = 0;
            int userFra = 0;
            int spouseFra = 0;
            if (Integer.parseInt(ssb.getBirth_year()) >= START_YEAR
                    && Integer.parseInt(ssb.getBirth_year()) <= END_YEAR) {
                userFra = RET_AGE5;
            } else {
                userFra = RET_AGE6;
            }
            jobject.put("userFRA", userFra);
            final JSONObject userJsonSSB = calculateUserSSB(spouseIncome, ssb.getMartialStatus(), userFra, spouseFra,
                    FIRST_BENDPOINT, SECOND_BENDPOINT, userAime, spouseAime);
            jobject.put("userSSB", userJsonSSB);
            if (ssb.getMartialStatus().equals("Married") && maritalStatusDB.equals("Yes")
                    && ssb.getGoalMarried().equals("No")) {
                spouseIncomeAraay = incomeJson.getJSONArray("spouse_income");
                combinedArray = incomeJson.getJSONArray("combined_income");
                spouseStartYear = Integer.parseInt(ssb.getSpouse_birth_year())
                        + Integer.parseInt(ssb.getSpouseRetirementAge());
                if (spouseIncomeAraay.length() > 0) {
                    spouseAime = findUserAIME(Integer.parseInt(ssb.getUserRetirementAge()), spouseIncomeAraay,
                            spouseStartYear);
                    if (Integer.parseInt(ssb.getSpouse_birth_year()) >= START_YEAR
                            && Integer.parseInt(ssb.getSpouse_birth_year()) <= END_YEAR) {
                        spouseFra = RET_AGE5;
                    } else {
                        spouseFra = RET_AGE6;
                    }
                    jobject.put("spouseFRA", spouseFra);
                    final JSONObject spousJsonSSB2 = calculateSpouseSSB(userFra, spouseFra, FIRST_BENDPOINT,
                            SECOND_BENDPOINT, userAime, spouseAime);
                    final JSONObject spouseJsonSSB = checkSpouseSSB(spousJsonSSB2, userJsonSSB,
                            Integer.parseInt(ssb.getUserRetirementAge()),
                            Integer.parseInt(ssb.getSpouseRetirementAge()));
                    jobject.put("spouseSSB", spouseJsonSSB);
                }
            } else if (ssb.getMartialStatus().equals("Married") && maritalStatusDB.equals("No")
                    && ssb.getGoalMarried().equals("No")) {
                if (ssb.getSpouse_birth_year() != "") {
                    JSONObject response2 = new JSONObject();
                    response2 = displaySSBLess(ssb, "spouse");
                    if ((response2.getString("status").equals("halfOfUserSSB"))) {
                        final double spouseSSBTemp = Double.parseDouble(userJsonSSB.getString("userssb")) / 2;
                        final JSONObject tempObj = new JSONObject();
                        tempObj.put("spousessb", df2.format(spouseSSBTemp));
                        tempObj.put("Spouse62", df2.format(Double.parseDouble(userJsonSSB.getString("User62")) / 2));
                        tempObj.put("Spouse63", df2.format(Double.parseDouble(userJsonSSB.getString("User63")) / 2));
                        tempObj.put("Spouse64", df2.format(Double.parseDouble(userJsonSSB.getString("User64")) / 2));
                        tempObj.put("Spouse65", df2.format(Double.parseDouble(userJsonSSB.getString("User65")) / 2));
                        tempObj.put("Spouse66", df2.format(Double.parseDouble(userJsonSSB.getString("User66")) / 2));
                        tempObj.put("Spouse67", df2.format(Double.parseDouble(userJsonSSB.getString("User67")) / 2));
                        tempObj.put("Spouse68", df2.format(Double.parseDouble(userJsonSSB.getString("User68")) / 2));
                        tempObj.put("Spouse69", df2.format(Double.parseDouble(userJsonSSB.getString("User69")) / 2));
                        tempObj.put("Spouse70", df2.format(Double.parseDouble(userJsonSSB.getString("User70")) / 2));
                        jobject.put("spouseSSB", tempObj);
                    } else {
                        final JSONObject responseSpouse = checkSpouseSSB(response2, userJsonSSB,
                                Integer.parseInt(ssb.getUserRetirementAge()),
                                Integer.parseInt(ssb.getSpouseRetirementAge()));
                        jobject.put("spouseSSB", responseSpouse);
                        jobject.put("spouseFRA", response2.getInt("spouseFRA"));
                    }
                } else {
                    final double spouseSSBTemp = Double.parseDouble(userJsonSSB.getString("userssb")) / 2;
                    final JSONObject tempObj = new JSONObject();
                    tempObj.put("spousessb", df2.format(spouseSSBTemp));
                    tempObj.put("Spouse62", df2.format(Double.parseDouble(userJsonSSB.getString("User62")) / 2));
                    tempObj.put("Spouse63", df2.format(Double.parseDouble(userJsonSSB.getString("User63")) / 2));
                    tempObj.put("Spouse64", df2.format(Double.parseDouble(userJsonSSB.getString("User64")) / 2));
                    tempObj.put("Spouse65", df2.format(Double.parseDouble(userJsonSSB.getString("User65")) / 2));
                    tempObj.put("Spouse66", df2.format(Double.parseDouble(userJsonSSB.getString("User66")) / 2));
                    tempObj.put("Spouse67", df2.format(Double.parseDouble(userJsonSSB.getString("User67")) / 2));
                    tempObj.put("Spouse68", df2.format(Double.parseDouble(userJsonSSB.getString("User68")) / 2));
                    tempObj.put("Spouse69", df2.format(Double.parseDouble(userJsonSSB.getString("User69")) / 2));
                    tempObj.put("Spouse70", df2.format(Double.parseDouble(userJsonSSB.getString("User70")) / 2));
                    jobject.put("spouseSSB", tempObj);
                }

            } else if (ssb.getMartialStatus().equals("Married") && maritalStatusDB.equals("No")
                    && ssb.getGoalMarried().equals("Yes")) {
                spouseIncomeAraay = incomeJson.getJSONArray("spouse_income");
                combinedArray = incomeJson.getJSONArray("combined_income");
                if (ssb.getSpouse_birth_year() != "") {
                    spouseStartYear = Integer.parseInt(ssb.getSpouse_birth_year())
                            + Integer.parseInt(ssb.getSpouseRetirementAge());
                    if (spouseIncomeAraay.length() > 0) {
                        spouseAime = findUserAIME(Integer.parseInt(ssb.getUserRetirementAge()), spouseIncomeAraay,
                                spouseStartYear);
                        if (Integer.parseInt(ssb.getSpouse_birth_year()) >= START_YEAR
                                && Integer.parseInt(ssb.getSpouse_birth_year()) <= END_YEAR) {
                            spouseFra = RET_AGE5;
                        } else {
                            spouseFra = RET_AGE6;
                        }
                        jobject.put("spouseFRA", spouseFra);
                        final JSONObject spousJsonSSB = calculateSpouseSSB(userFra, spouseFra, FIRST_BENDPOINT,
                                SECOND_BENDPOINT, userAime, spouseAime);
                        final JSONObject responseSpouse = checkSpouseSSB(spousJsonSSB, userJsonSSB,
                                Integer.parseInt(ssb.getUserRetirementAge()),
                                Integer.parseInt(ssb.getSpouseRetirementAge()));
                        jobject.put("spouseSSB", responseSpouse);
                    } else {
                        if (ssb.getSpouseWork().equals("No")) {
                            final double spouseSSBTemp = Double.parseDouble(userJsonSSB.getString("userssb")) / 2;
                            final JSONObject tempObj = new JSONObject();
                            tempObj.put("spousessb", df2.format(spouseSSBTemp));
                            tempObj.put("Spouse62",
                                    df2.format(Double.parseDouble(userJsonSSB.getString("User62")) / 2));
                            tempObj.put("Spouse63",
                                    df2.format(Double.parseDouble(userJsonSSB.getString("User63")) / 2));
                            tempObj.put("Spouse64",
                                    df2.format(Double.parseDouble(userJsonSSB.getString("User64")) / 2));
                            tempObj.put("Spouse65",
                                    df2.format(Double.parseDouble(userJsonSSB.getString("User65")) / 2));
                            tempObj.put("Spouse66",
                                    df2.format(Double.parseDouble(userJsonSSB.getString("User66")) / 2));
                            tempObj.put("Spouse67",
                                    df2.format(Double.parseDouble(userJsonSSB.getString("User67")) / 2));
                            tempObj.put("Spouse68",
                                    df2.format(Double.parseDouble(userJsonSSB.getString("User68")) / 2));
                            tempObj.put("Spouse69",
                                    df2.format(Double.parseDouble(userJsonSSB.getString("User69")) / 2));
                            tempObj.put("Spouse70",
                                    df2.format(Double.parseDouble(userJsonSSB.getString("User70")) / 2));
                            jobject.put("spouseSSB", tempObj);
                        } else {
                            JSONObject response2 = new JSONObject();
                            response2 = displaySSBLess(ssb, "spouse");
                            if ((response2.getString("status").equals("halfOfUserSSB"))) {
                                final double spouseSSBTemp = Double.parseDouble(userJsonSSB.getString("userssb")) / 2;
                                final JSONObject tempObj = new JSONObject();
                                tempObj.put("spousessb", df2.format(spouseSSBTemp));
                                tempObj.put("Spouse62",
                                        df2.format(Double.parseDouble(userJsonSSB.getString("User62")) / 2));
                                tempObj.put("Spouse63",
                                        df2.format(Double.parseDouble(userJsonSSB.getString("User63")) / 2));
                                tempObj.put("Spouse64",
                                        df2.format(Double.parseDouble(userJsonSSB.getString("User64")) / 2));
                                tempObj.put("Spouse65",
                                        df2.format(Double.parseDouble(userJsonSSB.getString("User65")) / 2));
                                tempObj.put("Spouse66",
                                        df2.format(Double.parseDouble(userJsonSSB.getString("User66")) / 2));
                                tempObj.put("Spouse67",
                                        df2.format(Double.parseDouble(userJsonSSB.getString("User67")) / 2));
                                tempObj.put("Spouse68",
                                        df2.format(Double.parseDouble(userJsonSSB.getString("User68")) / 2));
                                tempObj.put("Spouse69",
                                        df2.format(Double.parseDouble(userJsonSSB.getString("User69")) / 2));
                                tempObj.put("Spouse70",
                                        df2.format(Double.parseDouble(userJsonSSB.getString("User70")) / 2));
                                jobject.put("spouseSSB", tempObj);
                            } else {
                                final JSONObject responseSpouse = checkSpouseSSB(response2, userJsonSSB,
                                        Integer.parseInt(ssb.getUserRetirementAge()),
                                        Integer.parseInt(ssb.getSpouseRetirementAge()));
                                jobject.put("spouseSSB", responseSpouse);
                                jobject.put("spouseFRA", response2.getInt("spouseFRA"));
                            }
                        }

                    }
                } else {
                    final double spouseSSBTemp = Double.parseDouble(userJsonSSB.getString("userssb")) / 2;
                    final JSONObject tempObj = new JSONObject();
                    tempObj.put("spousessb", df2.format(spouseSSBTemp));
                    tempObj.put("Spouse62", df2.format(Double.parseDouble(userJsonSSB.getString("User62")) / 2));
                    tempObj.put("Spouse63", df2.format(Double.parseDouble(userJsonSSB.getString("User63")) / 2));
                    tempObj.put("Spouse64", df2.format(Double.parseDouble(userJsonSSB.getString("User64")) / 2));
                    tempObj.put("Spouse65", df2.format(Double.parseDouble(userJsonSSB.getString("User65")) / 2));
                    tempObj.put("Spouse66", df2.format(Double.parseDouble(userJsonSSB.getString("User66")) / 2));
                    tempObj.put("Spouse67", df2.format(Double.parseDouble(userJsonSSB.getString("User67")) / 2));
                    tempObj.put("Spouse68", df2.format(Double.parseDouble(userJsonSSB.getString("User68")) / 2));
                    tempObj.put("Spouse69", df2.format(Double.parseDouble(userJsonSSB.getString("User69")) / 2));
                    tempObj.put("Spouse70", df2.format(Double.parseDouble(userJsonSSB.getString("User70")) / 2));
                    jobject.put("spouseSSB", tempObj);
                }
            }
            jobject.put("status", "success");
            return jobject;
        } catch (final Exception e) {
            System.out.println("Exception:: " + e);
            return null;
        }

    }

    public JSONObject calculateSpouseSSB(final int useFfra, final int spouseFfra, final double firetBendPoint,
            final double secondBendPoint, final double userAime, final double spouseAime) {

        int spouseFra = spouseFfra;
        if (spouseFra == 0) {
            spouseFra = useFfra;
        }
        final JSONObject jsonSSB = new JSONObject();

        double spouseSsb = 0;
        try {
            spouseSsb = calculateSSB(firetBendPoint, secondBendPoint, spouseAime);
            if (spouseSsb < 0) {
                spouseSsb = 0;
            }
            if (spouseFfra == RET_AGE5) {
                jsonSSB.put("spousessb", df2.format(spouseSsb));
                jsonSSB.put("Spouse62", df2.format(spouseSsb * SSB_CONSTANT2 * MONTHS));
                jsonSSB.put("Spouse63", df2.format(spouseSsb * SSB_CONSTANT3 * MONTHS));
                jsonSSB.put("Spouse64", df2.format(spouseSsb * SSB_CONSTANT4 * MONTHS));
                jsonSSB.put("Spouse65", df2.format(spouseSsb * SSB_CONSTANT5 * MONTHS));
                jsonSSB.put("Spouse66", df2.format(spouseSsb * SSB_CONSTANT6 * MONTHS));
                jsonSSB.put("Spouse67", df2.format(spouseSsb * SSB_CONSTANT7 * MONTHS));
                jsonSSB.put("Spouse68", df2.format(spouseSsb * SSB_CONSTANT8 * MONTHS));
                jsonSSB.put("Spouse69", df2.format(spouseSsb * SSB_CONSTANT9 * MONTHS));
                jsonSSB.put("Spouse70", df2.format(spouseSsb * SSB_CONSTANT10 * MONTHS));

            } else if (spouseFfra == RET_AGE6) {
                jsonSSB.put("spousessb", df2.format(spouseSsb));
                jsonSSB.put("Spouse62", df2.format(spouseSsb * SSB_CONSTANT1 * MONTHS));
                jsonSSB.put("Spouse63", df2.format(spouseSsb * SSB_CONSTANT2 * MONTHS));
                jsonSSB.put("Spouse64", df2.format(spouseSsb * SSB_CONSTANT3 * MONTHS));
                jsonSSB.put("Spouse65", df2.format(spouseSsb * SSB_CONSTANT4 * MONTHS));
                jsonSSB.put("Spouse66", df2.format(spouseSsb * SSB_CONSTANT5 * MONTHS));
                jsonSSB.put("Spouse67", df2.format(spouseSsb * SSB_CONSTANT6 * MONTHS));
                jsonSSB.put("Spouse68", df2.format(spouseSsb * SSB_CONSTANT7 * MONTHS));
                jsonSSB.put("Spouse69", df2.format(spouseSsb * SSB_CONSTANT8 * MONTHS));
                jsonSSB.put("Spouse70", df2.format(spouseSsb * SSB_CONSTANT9 * MONTHS));

            }
            return jsonSSB;
        } catch (final Exception e) {

            e.printStackTrace();
            return jsonSSB;
        }
    }

}
