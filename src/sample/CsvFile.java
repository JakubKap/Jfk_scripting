package src.sample;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvFile {

    public CsvFile() {
    }

    public List<Entity> readFromCsvFile(String fileLocation){
        List<Entity> entities = new ArrayList<>();
        BufferedReader br = null;
        final String COMMA_DELIMITER= ",";

        try{
            br = new BufferedReader(new FileReader(fileLocation));
            String line = "";
            br.readLine();

            while ((line = br.readLine()) != null)
            {
                String[] entityDetails = line.split(COMMA_DELIMITER);
                if(entityDetails.length > 0 )
                {
                    Entity emp = new Entity(entityDetails[0],
                            entityDetails[1],
                            Integer.parseInt(entityDetails[2]),
                            Boolean.parseBoolean(entityDetails[3]),
                            Boolean.parseBoolean(entityDetails[4]),
                            Integer.parseInt(entityDetails[5]),
                            Integer.parseInt(entityDetails[6]));
                    entities.add(emp);
                }
            }

            for(Entity entity : entities)
            {
                System.out.println(entity.getName()+"   "+entity.getSurname()+"   "
                        +entity.getAge()+"   "+entity.isEmployed()
                        +"   "+entity.isMarried() +"   "+entity.getMonthSalary()
                        +"   "+entity.getNumOfChildren());
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return entities;
    }
}
