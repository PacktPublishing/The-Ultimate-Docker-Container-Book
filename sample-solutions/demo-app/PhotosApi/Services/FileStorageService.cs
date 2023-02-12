using System.Reflection;

public class FileStorageService
{
  public async Task UploadPhoto(IFormFile file, string bucketName = "photos")
  {
    // var filePath = Path.GetTempFileName();
    var filePath = Assembly.GetExecutingAssembly().Location;
    var dir = Path.Combine(Path.GetDirectoryName(filePath), bucketName);
    if(!Directory.Exists(dir))
    {
      Directory.CreateDirectory(dir);
    }
    filePath = Path.Combine(dir, file.FileName);

    using (var stream = System.IO.File.Create(filePath))
    {
        await file.CopyToAsync(stream);
    }
    Console.WriteLine($"Wrote '{file.FileName}' file to {filePath}");
  }
}
