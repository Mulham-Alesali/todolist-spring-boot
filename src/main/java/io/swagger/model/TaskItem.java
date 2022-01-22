package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * TaskItem
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-01-19T22:52:30.362Z[GMT]")


public class TaskItem   {
  @JsonProperty("taskId")
  private BigDecimal taskId = null;

  @JsonProperty("userEmail")
  private String userEmail = null;

  @JsonProperty("task")
  private String task = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("priority")
  private BigDecimal priority = null;
  
  public BigDecimal getStatus() {
	return status;
}

public void setStatus(BigDecimal status) {
	this.status = status;
}

@JsonProperty("status")
  private BigDecimal status = null;

  public TaskItem taskId(BigDecimal taskId) {
    this.taskId = taskId;
    return this;
  }

  /**
   * Get taskId
   * @return taskId
   **/
  @Schema(example = "3", description = "")
  
    @Valid
    public BigDecimal getTaskId() {
    return taskId;
  }

  public void setTaskId(BigDecimal taskId) {
    this.taskId = taskId;
  }

  public TaskItem userEmail(String userEmail) {
    this.userEmail = userEmail;
    return this;
  }

  /**
   * Get userEmail
   * @return userEmail
   **/
  @Schema(example = "fathi@gmail.com", required = true, description = "")
      @NotNull

    public String getUserEmail() {
    return userEmail;
  }

  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }

  public TaskItem task(String task) {
    this.task = task;
    return this;
  }

  /**
   * Get task
   * @return task
   **/
  @Schema(example = "write homework", required = true, description = "")
      @NotNull

  @Size(max=100)   public String getTask() {
    return task;
  }

  public void setTask(String task) {
    this.task = task;
  }

  public TaskItem description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
   **/
  @Schema(example = "mathe1 aufgabeblatt 2", description = "")
  
    public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public TaskItem priority(BigDecimal priority) {
    this.priority = priority;
    return this;
  }

  /**
   * Get priority
   * minimum: 1
   * maximum: 5
   * @return priority
   **/
  @Schema(example = "3", required = true, description = "")
      @NotNull

    @Valid
  @DecimalMin("1") @DecimalMax("5")   public BigDecimal getPriority() {
    return priority;
  }

  public void setPriority(BigDecimal priority) {
    this.priority = priority;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TaskItem taskItem = (TaskItem) o;
    return Objects.equals(this.taskId, taskItem.taskId) &&
        Objects.equals(this.userEmail, taskItem.userEmail) &&
        Objects.equals(this.task, taskItem.task) &&
        Objects.equals(this.description, taskItem.description) &&
        Objects.equals(this.priority, taskItem.priority);
  }

  @Override
  public int hashCode() {
    return Objects.hash(taskId, userEmail, task, description, priority);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TaskItem {\n");
    
    sb.append("    taskId: ").append(toIndentedString(taskId)).append("\n");
    sb.append("    userEmail: ").append(toIndentedString(userEmail)).append("\n");
    sb.append("    task: ").append(toIndentedString(task)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    priority: ").append(toIndentedString(priority)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
