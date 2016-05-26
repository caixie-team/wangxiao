package co.bluepx.edu.core.common.wordfilter;

public class FilteredResult
{
  private Integer level;
  private String filteredContent;
  private String badWords;
  private String originalContent;

  public String getBadWords()
  {
    return this.badWords;
  }

  public void setBadWords(String badWords) {
    this.badWords = badWords;
  }

  public FilteredResult()
  {
  }

  public FilteredResult(String originalContent, String filteredContent, Integer level, String badWords) {
    this.originalContent = originalContent;
    this.filteredContent = filteredContent;
    this.level = level;
    this.badWords = badWords;
  }

  public Integer getLevel() {
    return this.level;
  }

  public void setLevel(Integer level) {
    this.level = level;
  }

  public String getFilteredContent() {
    return this.filteredContent;
  }

  public void setFilteredContent(String filteredContent) {
    this.filteredContent = filteredContent;
  }

  public String getOriginalContent() {
    return this.originalContent;
  }

  public void setOriginalContent(String originalContent) {
    this.originalContent = originalContent;
  }
}