using Microsoft.AspNetCore.Mvc;
using PhotosApi.Photos;
using PhotosApi.Data;
using PhotosApi.Models;

namespace PhotosApi.Controllers;

[ApiController]
[Route("[controller]")]
public class PhotosController : ControllerBase
{
  private readonly PhotosDBContext context;

  public PhotosController(PhotosDBContext context)
    {
    this.context = context;
  }

    [HttpGet]
    public IEnumerable<PhotoDto> GetPhotos()
    {
      return context.Photos
        .Select(p => new PhotoDto(p.Id, p.SpeciesId, p.RaceId, p.Title, p.Description, p.Url))
        .ToList();
    }

    [HttpGet("buckets")]
    public async Task<IEnumerable<string>> GetFiles()
    {
      return await new BlobStorageService().ListBucketsAsync();
    }

    [HttpGet("{photoName}")]
    public async Task<IActionResult> GetPhoto(string photoName)
    {
      var stream = await new BlobStorageService().DownloadPhoto(photoName);
      return File(stream, "application/octet-stream");
    }

    [HttpPost]
    public async Task<IActionResult> UploadPhoto([FromForm]PhotoDetails photoDetails)
    {
      const string bucketName = "photos";
      var file = photoDetails.Photo;
      System.Console.WriteLine($"ID: {photoDetails.Id}, Title: {photoDetails.Title}, Description: {photoDetails.Description}, File name: {photoDetails.Photo.FileName}, File (length): {photoDetails.Photo.Length}");
      if (photoDetails.Photo.Length > 0)
      {
        photoDetails.Extension = file.FileName.Split('.')[1];
        await new BlobStorageService().UploadPhoto(photoDetails, bucketName);
        var photo = new Photo
        {
          Id = photoDetails.Id,
          SpeciesId = photoDetails.SpeciesId,
          RaceId = photoDetails.RaceId,
          Title = photoDetails.Title,
          Description = photoDetails.Description,
          Extension = photoDetails.Extension,
          Url = $"{bucketName}/{photoDetails.Id}.{photoDetails.Extension}"
        };
        context.Photos.Add(photo);
        await context.SaveChangesAsync();
      }
      return Ok(new { photoDetails.Photo.Length });
    }
}
