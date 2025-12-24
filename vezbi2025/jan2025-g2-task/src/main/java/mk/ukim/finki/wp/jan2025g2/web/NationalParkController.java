package mk.ukim.finki.wp.jan2025g2.web;

import mk.ukim.finki.wp.jan2025g2.model.ParkType;

public class NationalParkController {

    /**
     * This method should use the "list.html" template to display all national parks.
     * The method should be mapped on paths '/' and '/national-parks'.
     * The arguments that this method takes are optional and can be 'null'.
     * The filtered national parks that are the result of the call
     * findPage method from the NationalParkService should be displayed.
     * If you want to return a paginated result, you should also pass the page number and the page size as arguments.
     *
     * @param name       Filters national parks whose names contain the specified text
     * @param areaSize   Filters by area size bigger than the specified value
     * @param rating     Filters by rating greater than the specified value
     * @param parkType   Filters by park type
     * @param locationId Filters by location
     * @param pageNum    The page number
     * @param pageSize   The number of items per page
     * @return The view "list.html"
     */
    public String listAll(String name,
                          Double areaSize,
                          Double rating,
                          ParkType parkType,
                          Long locationId,
                          Integer pageNum,
                          Integer pageSize) {
        return "";
    }

    /**
     * This method should display the "form.html" template.
     * The method should be mapped on path '/national-parks/add'.
     *
     * @return The view "form.html".
     */
    public String showAdd() {
        return "";
    }

    /**
     * This method should display the "form.html" template.
     * However, in this case, all 'input' elements should be filled with the appropriate value for the national park that is updated.
     * The method should be mapped on path '/national-parks/edit/[id]'.
     *
     * @return The view "form.html".
     */
    public String showEdit(Long id) {
        return "";
    }

    /**
     * This method should create a new national park given the arguments it takes.
     * The method should be mapped on path '/national-parks'.
     * After the national park is created, all national parks should be displayed.
     *
     * @param name       The name of the national park
     * @param areaSize   The area size
     * @param rating     The rating of the park
     * @param parkType   The park type
     * @param locationId The location ID
     * @return Redirects to the list of national parks on '/national-parks'
     */
    public String create(String name,
                         Double areaSize,
                         Double rating,
                         ParkType parkType,
                         Long locationId) {
        return "";
    }

    /**
     * This method should update an existing national park given the arguments it takes.
     * The method should be mapped on path '/national-parks/[id]'.
     * After the national park is updated, all national parks should be displayed.
     *
     * @param id         The ID of the national park to update
     * @param name       The name of the national park
     * @param areaSize   The area size
     * @param rating     The rating of the park
     * @param parkType   The park type
     * @param locationId The location ID
     * @return Redirects to the list of national parks on '/national-parks'
     */
    public String update(Long id,
                         String name,
                         Double areaSize,
                         Double rating,
                         ParkType parkType,
                         Long locationId) {
        return "";
    }

    /**
     * This method should delete the national park that has the appropriate identifier.
     * The method should be mapped on path '/national-parks/delete/[id]'.
     * After the national parks is deleted, all national parks should be displayed on '/national-parks'.
     *
     * @param id The ID of the national park to delete
     * @return Redirects to the list of national parks
     */
    public String delete(Long id) {
        return "";
    }

    /**
     * This method should close the national parks that has the appropriate identifier.
     * The method should be mapped on path '/national-parks/close/[id]'.
     * After the selected national park is closed, all national parks should be displayed on '/national-parks'.
     *
     * @param id The ID of the national park to close
     * @return Redirects to the list of national parks
     */
    public String close(Long id) {
        return "";
    }
}

