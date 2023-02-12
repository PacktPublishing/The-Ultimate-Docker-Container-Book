using Microsoft.AspNetCore.Mvc;

namespace PhotosApi.Models;

public class PhotoDetails
{
  public Guid Id { get; set; }
  public int SpeciesId { get; set; }
  public int RaceId { get; set; }
  public string Title { get; set; }
  public string Extension { get; set; }
  public string? Description { get; set; }
  [FromForm(Name = "file")]
  public IFormFile Photo { get; set; }
}