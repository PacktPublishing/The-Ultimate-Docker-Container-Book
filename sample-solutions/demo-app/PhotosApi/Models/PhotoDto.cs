namespace PhotosApi.Photos;

public record PhotoDto(Guid Id, int speciesId, int raceId, string Title, string? Description, string Url);
