namespace PhotosApi.Data
{
  public class Comment
  {
    public Guid Id { get; set; }
    public Guid PhotoId { get; set; }
    public string Text { get; set; }
    public string UserId { get; set; }
  }
}