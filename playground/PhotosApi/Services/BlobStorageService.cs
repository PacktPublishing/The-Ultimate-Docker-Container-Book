using System.Reflection;
using Minio;
using PhotosApi.Models;

public class BlobStorageService
{
  private readonly string endpoint;
  private readonly MinioClient minio;

  public BlobStorageService()
  {
    endpoint = Environment.GetEnvironmentVariable("MINIO_ENDPOINT") ?? "localhost:9000";
    System.Console.WriteLine($"Minio endpoint: {endpoint}");
    minio = new MinioClient()
      .WithEndpoint(endpoint)
      .WithCredentials("ROOTNAME", "CHANGEME123")
      //.WithSSL()
      .Build();
  }

  public async Task<IEnumerable<string>> ListBucketsAsync()
  {
      var result = await minio.ListBucketsAsync();
      return result.Buckets
        .Select(bucket => $"{bucket.Name} {bucket.CreationDateDateTime}")
        .ToList();
  }

  public async Task UploadPhoto(PhotoDetails photoDetails, string bucketName = "photos")
  {
    var beargs = new BucketExistsArgs()
      .WithBucket(bucketName);

    bool found = await minio.BucketExistsAsync(beargs);
    if (!found)
    {
      var mbargs = new MakeBucketArgs()
        .WithBucket(bucketName);
        await minio.MakeBucketAsync(mbargs);
    }

    var stream = new MemoryStream();
    await photoDetails.Photo.CopyToAsync(stream);
    stream.Position = 0;
    
    var args = new PutObjectArgs()
      .WithBucket(bucketName)
      .WithObject($"{photoDetails.Id}.{photoDetails.Extension}")
      .WithStreamData(stream)
      .WithObjectSize(stream.Length)
      .WithContentType("application/octet-stream");
    // Upload a file to bucket.
    await minio.PutObjectAsync(args);
  }

  private string GetFilePath(string fileName)
  {
    var filePath = Assembly.GetExecutingAssembly().Location;
    var dir = Path.Combine(Path.GetDirectoryName(filePath), "photos");
    if(!Directory.Exists(dir))
    {
      Directory.CreateDirectory(dir);
    }
    filePath = Path.Combine(dir, fileName);
    return filePath;
  }

  public async Task<Stream> DownloadPhoto(string objectName, string bucketName = "photos")
  {
    var fileName = GetFilePath(objectName);
    var args = new GetObjectArgs()
      .WithBucket(bucketName)
      .WithObject(objectName)
      .WithCallbackStream(stream => {
        var fileStream = File.Create(fileName);
        stream.CopyTo(fileStream);
        fileStream.Dispose();
        var info = new FileInfo(fileName);
        var file_read_size = info.Length;
        // Uncomment to print the file on output console
        // stream.CopyTo(Console.OpenStandardOutput());
        Console.WriteLine(
            $"Successfully downloaded object to {info.FullName} (length = {info.Length})");
        stream.Dispose();
      });
    
    var res = await minio.GetObjectAsync(args);

    var ms = new MemoryStream();
    ms.Position = 0;
    System.Console.WriteLine($"Length: {ms.Length}");
    return ms;
  }
}