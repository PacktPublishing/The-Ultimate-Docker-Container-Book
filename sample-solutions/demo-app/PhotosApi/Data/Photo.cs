namespace PhotosApi.Data
{
  public class Photo
  {
    public Guid Id { get; set; }
    public int SpeciesId { get; set; }
    public int RaceId { get; set; }
    public string Title { get; set; }
    public string? Description { get; set; }
    public string Extension { get; set; }
    public string Url { get; set; }
  }
}