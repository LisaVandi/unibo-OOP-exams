package a02b.e1;

import java.util.Set;

import a02b.e1.UniversityProgram.Sector;

import java.util.Map; 
import java.util.HashMap;

public class UniversityProgramFactoryImpl implements UniversityProgramFactory {

    @Override
    /**
	 * @return a program where a selection of courses is ok if:
	 * - overall they have 60 credits
	 */
    public UniversityProgram flexible() {
        final Map<String, Pair<Sector,Integer>> coursesMap = new HashMap<>();

        return new UniversityProgram() {

            @Override
            public void addCourse(String name, Sector sector, int credits) {
                coursesMap.put(name, new Pair<UniversityProgram.Sector,Integer>(sector, credits));
            }

            @Override
            public boolean isValid(Set<String> courseNames) {
                int totalCredits = 0;
                for (String courseName : courseNames) {
                    Pair<UniversityProgram.Sector, Integer> course = coursesMap.get(courseName);
                    if (course != null) {
                        totalCredits += course.get2();
                    }
                }
                return totalCredits == 60; 
            }
            
        };
    }

    @Override
    /**
	 * @return a program where a selection of courses is ok if:
	 * - overall they have 60 credits
	 * - MATH courses have >= 12 credits
	 * - COMPUTER_SCIENCE courses have >= 12 credits
	 * - PHYSICS courses have >= 12 credits
	 */
    public UniversityProgram scientific() {
        final Map<String, Pair<Sector,Integer>> coursesMap = new HashMap<>();

        return new UniversityProgram() {

            @Override
            public void addCourse(String name, Sector sector, int credits) {
                coursesMap.put(name, new Pair<UniversityProgram.Sector,Integer>(sector, credits));
            }

            @Override
            public boolean isValid(Set<String> courseNames) {
                int totalCredits = 0;
                int mathCredits = 0; 
                int physicsCredits = 0;
                int csCredits = 0; 

                for (String string : courseNames) {
                    Pair<UniversityProgram.Sector, Integer> courseValue = coursesMap.get(string);
                    if (courseValue != null) {
                        totalCredits += courseValue.get2();
                        if (courseValue.get1() == Sector.MATHEMATICS) {
                            mathCredits += courseValue.get2();
                        } else if (courseValue.get1() == Sector.PHYSICS) {
                            physicsCredits += courseValue.get2();
                        } else if (courseValue.get1() == Sector.COMPUTER_SCIENCE) {
                            csCredits += courseValue.get2();
                        }  
                    } 
                    
                }
                if (totalCredits != 60 ) {
                    return false;
                }
                if (mathCredits < 12 || physicsCredits < 12 || csCredits < 12) {
                    return false;
                }
                return true;
            }
            
        };
    }

    @Override
    /**
	 * @return a program where a selection of courses is ok if:
	 * - overall they have >=48 credits
	 * - COMPUTER_SCIENCE and COMPUTER_ENGINEERING together have >= 30 credits
	 */
    public UniversityProgram shortComputerScience() {
        return new UniversityProgram() {

            private Map<String, Pair<Sector,Integer>> coursesMap = new HashMap<>();

            @Override
            public void addCourse(String name, Sector sector, int credits) {
                coursesMap.put(name, new Pair<UniversityProgram.Sector,Integer>(sector, credits));
            }

            @Override
            public boolean isValid(Set<String> courseNames) {
                int totalCredits = 0;
                int ceCredits = 0;
                int csCredits = 0; 

                for (String string : courseNames) {
                    Pair<UniversityProgram.Sector, Integer> courseValue = coursesMap.get(string);
                    if (courseValue != null) {
                        totalCredits += courseValue.get2();
                        if (courseValue.get1() == Sector.COMPUTER_ENGINEERING) {
                            ceCredits += courseValue.get2();
                        } else if (courseValue.get1() == Sector.COMPUTER_SCIENCE) {
                            csCredits += courseValue.get2();
                        }  
                    } 
                    
                }
                if (totalCredits < 48 ) {
                    return false;
                }
                if (csCredits + ceCredits < 30) {
                    return false;
                }
                return true;
            }
            
        };
    }

    @Override
    /**
	 * @return a program where a selection of courses is ok if:
	 * - overall they have =120 credits
	 * - COMPUTER_SCIENCE and COMPUTER_ENGINEERING together have >= 60 credits
	 * - MATH and PHYSICS together have <= 18 credits
	 * - THESIS is = 24 credit
	 */
    public UniversityProgram realistic() {
        return new UniversityProgram() {
            private Map<String, Pair<Sector,Integer>> coursesMap = new HashMap<>();

            @Override
            public void addCourse(String name, Sector sector, int credits) {
                coursesMap.put(name, new Pair<UniversityProgram.Sector,Integer>(sector, credits));
            }


            @Override
            public boolean isValid(Set<String> courseNames) {
                int totalCredits = 0;
                int ceCredits = 0;
                int csCredits = 0; 
                int physicsCredits = 0;
                int mathCredits = 0;
                int thesisCredits = 0;

                for (String string : courseNames) {
                    Pair<UniversityProgram.Sector, Integer> courseValue = coursesMap.get(string);
                    if (courseValue != null) {
                        totalCredits += courseValue.get2();
                        if (courseValue.get1() == Sector.COMPUTER_ENGINEERING) {
                            ceCredits += courseValue.get2();
                        } else if (courseValue.get1() == Sector.COMPUTER_SCIENCE) {
                            csCredits += courseValue.get2();
                        } else if (courseValue.get1() == Sector.MATHEMATICS) {
                            mathCredits += courseValue.get2();
                        } else if (courseValue.get1() == Sector.PHYSICS) {
                            physicsCredits += courseValue.get2();
                        } else if (courseValue.get1() == Sector.THESIS) {
                            thesisCredits += courseValue.get2();
                        }
                    } 
                    
                }
                return totalCredits == 120 &&
                csCredits + ceCredits >= 60 &&
                mathCredits + physicsCredits <= 18 &&
                thesisCredits == 24;  
            }
            
        };    
    }

}
