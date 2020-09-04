package exam.courseContext.userInterface;

import exam.courseContext.application.CourseApplicationService;
import exam.courseContext.application.CreateCourseCommand;
import exam.courseContext.domain.model.course.Course;
import exam.courseContext.domain.model.course.CourseId;
import exam.courseContext.domain.model.course.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CourseController {
    private final CourseRepository courseRepository;
    private final CourseApplicationService courseApplicationService;

    @Autowired
    public CourseController(CourseRepository courseRepository, CourseApplicationService courseApplicationService) {
        this.courseRepository = courseRepository;
        this.courseApplicationService = courseApplicationService;
    }

    @PostMapping("/courses")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    CourseDTO create(@RequestBody CreateCourseCommand createCourseCommand) {
        final Course course = courseApplicationService.createCourse(createCourseCommand);
        return CourseDTO.from(course);
    }

    @GetMapping("/courses")
    List<CourseDTO> getAll() {
        return courseRepository.getAll().stream().map(CourseDTO::from).collect(Collectors.toList());
    }

    @GetMapping("/courses/{courseId}")
    CourseDTO findOne(@PathVariable String courseId) {
        final CourseId id = new CourseId(courseId);
        return CourseDTO.from(courseRepository.find(id));
    }

    @PutMapping("/courses/{courseId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void update(@PathVariable String courseId, @RequestBody CreateCourseCommand createCourseCommand) {
       courseApplicationService.updateCourse(courseId, createCourseCommand);
    }

    @PatchMapping("/courses/{courseId}/publishing")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void publish(@PathVariable String courseId){
        courseApplicationService.publish(courseId);
    }

}
