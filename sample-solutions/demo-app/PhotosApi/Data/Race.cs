namespace PhotosApi.Data
{
  public class Race
  {
    public int Id { get; set; }
    public int SpeciesId { get; set; }
    public string Name { get; set; }
    public string? Description { get; set; }
  }
}